package com.trajectory27.contentproviderdemo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.trajectory27.contentproviderdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val contactList = arrayListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = layoutManager
        adapter = ContactAdapter(this, contactList)
        binding.recycleView.adapter = adapter

        XXPermissions.with(this).permission(Manifest.permission.READ_CONTACTS).request(object :
            OnPermissionCallback {
            override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                readContacts()
            }

            override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                if (doNotAskAgain) {
                    Toast.makeText(this@MainActivity, "请在设置中开启", Toast.LENGTH_SHORT).show()
                    XXPermissions.startPermissionActivity(this@MainActivity, permissions)
                } else {
                    Toast.makeText(this@MainActivity, "请允许读取联系人权限", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun readContacts() {
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                val displayName =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactList.add(Contact(displayName, number))
            }

        }
        adapter.notifyDataSetChanged()
    }
}
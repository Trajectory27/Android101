package com.trajectory27.contentproviderdemo

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Trajectory27
 * @description 联系人列表适配器
 * @date 2023/6/28 10:51
 */
class ContactAdapter(private val activity: Activity, private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val number: TextView = view.findViewById(R.id.tvNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.text = contact.name
        holder.number.text = contact.number
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}
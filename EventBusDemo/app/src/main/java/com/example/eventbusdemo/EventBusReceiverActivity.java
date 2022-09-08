package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eventbusdemo.databinding.ActivityEventBusReceiverBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.PublicKey;

public class EventBusReceiverActivity extends AppCompatActivity {
    private ActivityEventBusReceiverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBusReceiverBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventBusReceiverActivity.this, EventBusSenderActivity.class);
                startActivity(intent);
            }
        });

        //在onCreate()中注册
        EventBus.getDefault().register(this);
    }

    //订阅消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        binding.textView.setText(messageEvent.getMessage());
    }

    //重写onDestory(),注销EventBus。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
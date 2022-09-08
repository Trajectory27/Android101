package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.eventbusdemo.databinding.ActivityEventBusSenderBinding;

import org.greenrobot.eventbus.EventBus;

public class EventBusSenderActivity extends AppCompatActivity {
    private ActivityEventBusSenderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventBusSenderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送对象
                EventBus.getDefault().post(new MessageEvent("got message"));
                finish();
            }
        });
    }
}
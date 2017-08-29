package com.example.rent.application1;

import android.app.Application;

import com.example.rent.application1.component.DaggerNetComponent;
import com.example.rent.application1.component.NetComponent;
import com.example.rent.application1.module.NetModule;


public class MyApplication extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(this))
                .build();
    }

    //po co to tak naprawde??
    public NetComponent getNetComponent(){
        return netComponent;
    }
}
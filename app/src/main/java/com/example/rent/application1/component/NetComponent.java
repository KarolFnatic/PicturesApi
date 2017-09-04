package com.example.rent.application1.component;

import com.example.rent.application1.MainActivity;
import com.example.rent.application1.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
    void inject(MainActivity mainActivity);

}
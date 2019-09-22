package com.example.carousel.di.component

import com.example.carousel.di.module.AppModule
import com.example.carousel.di.module.NetModule
import com.example.carousel.room.ArtDAO
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Component(modules = [AppModule::class, NetModule::class])
@Singleton
interface NetComponent {

    fun retrofit():Retrofit
    fun artDao():ArtDAO
}
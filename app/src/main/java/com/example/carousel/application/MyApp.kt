package com.example.carousel.application

import android.app.Application
import com.example.carousel.di.component.CarouselComponent
import com.example.carousel.di.component.DaggerCarouselComponent
import com.example.carousel.di.component.DaggerNetComponent
import com.example.carousel.di.component.NetComponent
import com.example.carousel.di.module.AppModule
import com.example.carousel.di.module.CarouselModule
import com.example.carousel.di.module.NetModule

@Suppress("DEPRECATION")
class MyApp : Application() {

    var netComponent: NetComponent?=null
    var carouselComponent: CarouselComponent?=null

    override fun onCreate() {
        super.onCreate()

        netComponent = provideNetComponent(this)
        carouselComponent = provideCarouselComponent()
    }

    private fun provideNetComponent(application: Application): NetComponent{
        return DaggerNetComponent.builder()
            .appModule(AppModule(application))
            .netModule(NetModule())
            .build()
    }

    private fun provideCarouselComponent():CarouselComponent{
        return DaggerCarouselComponent.builder()
            .netComponent(netComponent)
            .carouselModule(CarouselModule())
            .build()
    }
}
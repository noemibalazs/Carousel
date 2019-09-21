package com.example.carousel.di.component

import com.example.carousel.di.module.CarouselModule
import com.example.carousel.di.scope.CarouselScope
import com.example.carousel.ui.MainActivity
import com.example.carousel.ui.SplashActivity
import dagger.Component

@Component(dependencies = [NetComponent::class], modules = [CarouselModule::class])
@CarouselScope
interface CarouselComponent {

    fun injectSplash(splash:SplashActivity)
    fun injectMain(main:MainActivity)
}
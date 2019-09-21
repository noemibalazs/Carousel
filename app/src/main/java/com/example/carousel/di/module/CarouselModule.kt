package com.example.carousel.di.module

import com.example.carousel.di.scope.CarouselScope
import com.example.carousel.net.CarouselService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CarouselModule {

    @Provides
    @CarouselScope
    fun provideCarouselService(retrofit: Retrofit): CarouselService{
        return retrofit.create(CarouselService::class.java)
    }
}
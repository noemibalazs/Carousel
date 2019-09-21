package com.example.carousel.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.carousel.room.ArtDAO
import com.example.carousel.room.ArtDataBase
import com.example.carousel.utils.ART_DATA_BASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val app:Application){

    @Provides
    @Singleton
    fun providesContext():Context = app

    @Provides
    @Singleton
    fun getArtDataBase(): ArtDataBase{
        return Room.databaseBuilder(app, ArtDataBase::class.java, ART_DATA_BASE).build()
    }

    @Provides
    @Singleton
    fun getArtDao(db: ArtDataBase):ArtDAO{
        return db.artDao()
    }
}
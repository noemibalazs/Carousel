package com.example.carousel.net

import androidx.lifecycle.LiveData
import com.example.carousel.data.ArtworkBlock
import retrofit2.http.GET
import retrofit2.http.Query

interface CarouselService {

    @GET("?")
    fun getArtWorks(@Query("key") key:String, @Query("per_page") numb:Int): LiveData<MutableList<ArtworkBlock>>
}
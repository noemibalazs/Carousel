package com.example.carousel.net

import com.example.carousel.data.ArtworkBlock
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CarouselService {

    @GET("?")
    fun getArtWorks(@Query("key") key:String, @Query("per_page") numb:Int, @Query("q") query: String): Call<ArtworkBlock>
}
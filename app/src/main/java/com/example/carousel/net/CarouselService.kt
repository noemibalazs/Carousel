package com.example.carousel.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.carousel.data.ArtworkBlock
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CarouselService {

    @GET("?")
    fun getArtWorks(@Query("key") key:String, @Query("per_page") numb:Int, @Query("q") query: String): Observable<ArtworkBlock>
}
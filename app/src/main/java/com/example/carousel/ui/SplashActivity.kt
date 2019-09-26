package com.example.carousel.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.example.carousel.application.MyApp
import com.example.carousel.data.ArtworkBlock
import com.example.carousel.net.CarouselService
import com.example.carousel.room.ArtDAO
import com.example.carousel.utils.KEY
import com.example.carousel.utils.art2EntityList
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var carouselService: CarouselService? = null

    @Inject
    @JvmField
    var artDao: ArtDAO? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.applicationContext as MyApp).carouselComponent?.injectSplash(this)

        getData()

        Handler().postDelayed({
            launch()
        }, 5000)

        val x = getListSize()
        Log.d("Splash", "the size is: $x")
    }

    private fun insertAll(myList: ArtworkBlock) {
        val list = myList.hits
        val newList = list.art2EntityList()
        doAsync {
            artDao?.addArt2DB(newList)
        }
    }

    private fun getData(){
       val request =  carouselService?.getArtWorks(KEY, 21, "puma")
        request?.enqueue(object : Callback<ArtworkBlock>{
            override fun onFailure(call: Call<ArtworkBlock>, th: Throwable) {
                Log.d("SplashActivity", "onFailure response: ${th.message}")
            }

            override fun onResponse(call: Call<ArtworkBlock>, response: Response<ArtworkBlock>) {
                if (response.body()!= null && response.isSuccessful){
                    val body = response.body()!!
                    insertAll(body)
                }

                if (!response.isSuccessful){
                    Log.d("SplashActivity", "onResponse message: ${response.code()}")
                }
            }
        })
    }

    private fun launch() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getListSize(): Int {
        var size = 0
        artDao?.getRows()!!.observe(this, Observer {
            size = it
        })
        return size
    }

}

package com.example.carousel.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.carousel.application.MyApp
import com.example.carousel.data.ArtworkBlock
import com.example.carousel.net.CarouselService
import com.example.carousel.room.ArtDAO
import com.example.carousel.utils.KEY
import com.example.carousel.utils.art2EntityList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var carouselService:CarouselService?=null

    @Inject
    @JvmField
    var artDao:ArtDAO?=null

    private var compositeDisposable:CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        compositeDisposable = CompositeDisposable()

        (this.applicationContext as MyApp).carouselComponent?.injectSplash(this)

        Handler().postDelayed({
            launch()
        }, 5000)
    }

    private fun insertAll(myList: ArtworkBlock){
        doAsync {
            artDao?.addArt2DB(myList.hits.art2EntityList())
        }
    }

    private fun getArtList(){

        compositeDisposable?.add(
            carouselService?.getArtWorks(KEY, 21, "puma")!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::insertAll)
        )
    }

    private fun launch(){
        getArtList()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}

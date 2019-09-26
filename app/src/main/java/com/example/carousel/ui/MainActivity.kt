package com.example.carousel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ViewSwitcher
import androidx.lifecycle.Observer
import com.example.carousel.R
import com.example.carousel.adapter.CarouselAdapter
import com.example.carousel.application.MyApp
import com.example.carousel.room.ArtDAO
import com.example.carousel.room.ArtEntity
import com.example.carousel.utils.getEntity
import com.example.carousel.utils.takeFirst
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var artDao: ArtDAO? = null

    private var list: MutableList<ArtEntity> = mutableListOf()
    private lateinit var adapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.applicationContext as MyApp).carouselComponent?.injectMain(this)

        setTextSwitcher()
        getListFromDB()
    }

    override fun onResume() {
        super.onResume()
        populateUI()
        onPositionScrolling()
    }

    private fun setTextSwitcher() {
        titleSwitcher.setFactory(object : ViewSwitcher.ViewFactory {
            override fun makeView(): View {
                val inflater = LayoutInflater.from(this@MainActivity)
                return inflater.inflate(R.layout.carousel_title, null)
            }
        })

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        titleSwitcher.inAnimation = fadeIn
        titleSwitcher.outAnimation = fadeOut
    }

    private fun getListFromDB() {
        artDao?.getArtList()!!.observe(this, Observer {
           list.addAll(it)
        })
    }

    private fun getArtEntity(position: Int): ArtEntity{
        if (position != 0){
            return list[position +1]
        }else{
            return list[position]
        }
    }

    private fun populateUI(){
        adapter = CarouselAdapter(list)
        coverflow.adapter = adapter
    }

    private fun onPositionScrolling(){
        coverflow.setOnScrollPositionListener(object : FeatureCoverFlow.OnScrollPositionListener{
            override fun onScrolling() {
                titleSwitcher.setText("")
            }

            override fun onScrolledToPosition(position: Int) {
                val entity = getArtEntity(position)
                titleSwitcher.setText(entity.tag.takeFirst())
            }
        })
    }

}

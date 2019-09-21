package com.example.carousel.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.carousel.R
import kotlinx.android.synthetic.main.activity_main.view.*

fun loadPicture(link:String, image:ImageView){
    Glide.with(image.context)
        .load(link)
        .error(R.drawable.tiger)
        .placeholder(R.drawable.tiger)
        .into(image)
}

fun String.takeFirst():String{
    val index = this.indexOf(",")
    return this.take(index)
}
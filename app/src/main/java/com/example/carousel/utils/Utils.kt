package com.example.carousel.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.carousel.R
import com.example.carousel.data.Artwork
import com.example.carousel.room.ArtEntity
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

fun Artwork.art2Entity():ArtEntity{
    return ArtEntity(this.tags, this.id, this.user, this.url)
}

fun List<Artwork>.art2EntityList(): MutableList<ArtEntity>{
    val myList: MutableList<ArtEntity> = mutableListOf()
    this.map { art -> myList.add(art.art2Entity())}
    return myList
}
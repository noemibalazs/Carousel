package com.example.carousel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.carousel.R
import com.example.carousel.room.ArtEntity
import com.example.carousel.utils.loadPicture
import com.example.carousel.utils.takeFirst

class CarouselAdapter(val myList:List<ArtEntity>, val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: CarouselVH
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_carousel, parent, false)
            vh = CarouselVH(view)
            view.tag = vh
        }else{
            view = convertView
            vh = view.tag as CarouselVH
        }

        val entity = myList[position]
        loadPicture(entity.url, vh.carouselAvatar)
        vh.carouselLabel.text = entity.tag.takeFirst()

        return view
    }

    override fun getItem(position: Int): Any {
       return myList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return myList.size
    }

    class CarouselVH(view: View){
        val carouselAvatar = view.findViewById<ImageView>(R.id.carousel)
        val carouselLabel = view.findViewById<TextView>(R.id.carouselName)
    }
}
package com.example.carousel.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDAO {

    @Query("SELECT * FROM art_entity")
    fun getArtList(): LiveData<MutableList<ArtEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArt2DB(artworks: MutableList<ArtEntity>)

    @Query("SELECT COUNT(id) FROM art_entity")
    fun getRows(): LiveData<Int>

}
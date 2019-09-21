package com.example.carousel.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art_entity")
data class ArtEntity(
        @PrimaryKey
        val tag: String,
        val id: Int,
        val user: String,
        val url: String
)
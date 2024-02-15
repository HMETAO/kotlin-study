package com.hmetao.gallery.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Pixabay(
    val total: Int,
    val totalHits: Int,
    val hits: List<PhotoItem>
)

@Parcelize
data class PhotoItem(
    val webformatURL: String,
    val id: Int,
    val largeImageURL: String
):Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoItem

        if (webformatURL != other.webformatURL) return false
        if (id != other.id) return false
        return largeImageURL == other.largeImageURL
    }

    override fun hashCode(): Int {
        var result = webformatURL.hashCode()
        result = 31 * result + id
        result = 31 * result + largeImageURL.hashCode()
        return result
    }
}
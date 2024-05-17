package com.houssem.fria.Room


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/****
 *  Represents a single furniture item
 *
 */
@Entity(tableName = "FavoriteProducts")
data class Furniture(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var category: String?,
    var color: String?,
    @Ignore
    var details: Map<String?, String?>,
    var images: List<String?>,
    var model: String?,
    var price: Int?,
    var rendable: String?,
    @Ignore
    var sizes: List<Long?>,
    var source: String?,
    var createdDate: Date
) : Serializable {
    constructor() : this(
        id=0,
        category = null,
        color = null,
        details = emptyMap<String?, String?>(),
        images = listOf<String?>(),
        model = null,
        price = null,
        rendable = null,
        sizes = listOf<Long?>(),
        source = null,
        createdDate = Date()
    )


}

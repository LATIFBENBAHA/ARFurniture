package com.latif.fria.Repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.latif.fria.Room.Furniture

interface IFavoritesDAO {
    fun   getAll(): LiveData<List<Furniture>>
    fun   AddToFavorites(vararg product: Furniture)
    fun   delete(dep: Furniture)
    fun   deleteAll()
}

@Dao
interface FavoritesDAO /*: IFavoritesDAO*/{
    @Query("SELECT * FROM FavoriteProducts ORDER BY createdDate DESC")
    fun getAll(): LiveData<List<Furniture>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun AddToFavorites(product: Furniture)

    @Delete()
    fun delete(product: Furniture)

    @Update()
    fun update(product: Furniture)

    @Query("Select * From FavoriteProducts WHERE id=:id")
    fun getItem(id: Int):Furniture

    @Query("Select EXISTS(SELECT * FROM FavoriteProducts WHERE id = :id)")
    fun any(id: Int):Boolean


}
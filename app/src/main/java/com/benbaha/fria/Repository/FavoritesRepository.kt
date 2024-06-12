package com.benbaha.fria.Repository

import androidx.lifecycle.LiveData
import com.benbaha.fria.Room.Furniture

class FavoritesRepository(private val dao: FavoritesDAO) {


    val favorites: LiveData<List<Furniture>> = dao.getAll()


     fun insert(item: Furniture) {
        dao.AddToFavorites(item)
    }

     fun delete(deleteWord: Furniture) {
        dao.delete(deleteWord)
    }

    fun any(id: Int) : Boolean {
        return dao.any(id)
    }

    fun getItem(id: Int) : Furniture {
        return dao.getItem(id)
    }

    fun getAllItems() : LiveData<List<Furniture>> {
        return dao.getAll()
    }

    /*fun deleteAll() {
       dao.deleteAll()
   }*/
}
package com.houssem.fria.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.houssem.fria.Room.AppDatabase
import com.houssem.fria.Repository.FavoritesRepository
import com.houssem.fria.Room.Furniture

class FavoritesViewModelFactory(
        private val app: Application)
    : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(
                        FavoritesViewModel::class.java)) {

            return FavoritesViewModel(app) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class FavoritesViewModel (application: Application) : AndroidViewModel(application){


   
        private val repository: FavoritesRepository
        // LiveData gives us updated words when they change.
        val favorites: LiveData<List<Furniture>>

        init {
            val dao = AppDatabase.getDatabase(application).favoritesDAO()
            repository = FavoritesRepository(dao)
            favorites = repository.favorites
        }

    fun insert(item: Furniture) {
        repository.insert(item)


    }
    fun get(id: Int) {
        repository.getItem(id)
    }

    fun any(id: Int):Boolean {
        return repository.any(id)
    }

    /*fun delete() {
            repository.deleteAll()

        }*/

        fun delete(item: Furniture) {
            repository.delete(item)
        }


    
}
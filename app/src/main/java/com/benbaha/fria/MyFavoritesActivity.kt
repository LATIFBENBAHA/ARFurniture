package com.benbaha.fria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.benbaha.fria.Room.Furniture
import com.benbaha.fria.ViewModel.FavoritesViewModel
import com.benbaha.fria.ViewModel.FavoritesViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_my_favorites.*
import kotlinx.android.synthetic.main.layout_department_single_list_item.view.*

class MyFavoritesActivity : AppCompatActivity() {

    lateinit var viewModal: FavoritesViewModel
    lateinit var factory: FavoritesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_favorites)
        supportActionBar?.title= "My favorites"
        factory = FavoritesViewModelFactory(application)
        viewModal = ViewModelProvider(this,factory).get(FavoritesViewModel::class.java)
        val adapter = GroupAdapter<GroupieViewHolder>()
        viewModal.favorites.observe(this, Observer{ favorites->
            // Data bind the recycler view
            favorites.forEach { adapter.add(FavoriteItem(it)) }

        })
           // adapter.add(FavoriteItem())
            //adapter.add(FavoriteItem())
            myfavorites.adapter = adapter

    }
}


class FavoriteItem(val item : Furniture) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.department_img.setImageResource(R.drawable.btn_context_menu)
        viewHolder.itemView.department_name.text = item.model
    }

    override fun getLayout(): Int {
        return R.layout.layout_department_single_list_item
    }

}
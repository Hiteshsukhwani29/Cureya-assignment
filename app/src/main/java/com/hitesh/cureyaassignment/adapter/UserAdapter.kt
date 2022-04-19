package com.hitesh.cureyaassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.cureyaassignment.R
import com.hitesh.cureyaassignment.User
import com.squareup.picasso.Picasso

class UserAdapter(private val mList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Picasso.get().load(itemsViewModel.imgurl).into(holder.userImage)
        holder.userName.text = itemsViewModel.name
        holder.userEmail.text = itemsViewModel.email
        holder.userCard.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var userCard: CardView = ItemView.findViewById(R.id.card_user)
        var userImage: ImageView = ItemView.findViewById(R.id.card_user_icon)
        var userName: TextView = ItemView.findViewById(R.id.card_user_name)
        val userEmail: TextView = ItemView.findViewById(R.id.card_user_email)
    }

}
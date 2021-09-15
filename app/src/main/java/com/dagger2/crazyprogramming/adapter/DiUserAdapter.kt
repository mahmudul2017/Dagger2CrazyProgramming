package com.dagger2.crazyprogramming.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dagger2.crazyprogramming.R
import com.dagger2.crazyprogramming.model.user.User
import kotlinx.android.synthetic.main.di_user_row.view.*

class DiUserAdapter(private var userList: ArrayList<User>, private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<DiUserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.di_user_row, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user = userList[position]
        holder.itemView.tvUserName.text = "Name: ${user.name}"
        holder.itemView.tvUserEmail.text = "Email: ${user.email}"
        holder.itemView.tvUserZipCode.text = "Zip Code: ${user.address.zipcode}"

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(user)
        }
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener {
        fun onItemClick(user: User?)
    }
}

package com.example.myapplication.ui.person_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.common.util.setImageFromURL
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse
import com.example.myapplication.databinding.PersonListItemBinding

class PersonListAdapter (
    private var personList: List<PersonDetailsEntity>,
    private val listener: PersonListAdapterListener,
) : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    interface PersonListAdapterListener {
        fun onItemClick(item: PersonDetailsEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PersonListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.person_list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding) {
        val item = personList[position]

        materialcardviewPersonItem.setOnClickListener { listener.onItemClick(item) }
        imageViewPerson.setImageFromURL(item.picture, progressBarImage)
        textViewName.text = item.name
        textViewLocation.text = item.location
        textViewEmail.text = item.email
    }

    override fun getItemCount() = personList.size

    fun updateData(data: List<PersonDetailsEntity>) {
        personList = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: PersonListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
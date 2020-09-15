package com.gemidroid.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemidroid.data.model.PopularPerson
import com.gemidroid.moviesdb.R
import com.gemidroid.util.Constants
import kotlinx.android.synthetic.main.popular_persons_item.view.*

class PopularPersonsAdapter(
    private val popularPersons: List<PopularPerson>,
    private val onPersonItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.popular_persons_item, parent, false)
        return object : RecyclerView.ViewHolder(rootView) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindData(position, holder)
    }

    private fun bindData(
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        val popularPerson = popularPersons[position]
        val builder = mutableListOf<String>()
        holder.itemView.apply {
            txt_name.text = popularPerson.name
            popularPerson.works.forEach {
                if (!it.title.isNullOrEmpty()) builder.add(it.title)
            }
            txt_works.text = builder.joinToString()
            Glide.with(context).load("${Constants.IMAGE_PATH}${popularPerson.imgProfile}")
                .error(R.drawable.ic_launcher_foreground)
                .into(img_profile)
            setOnClickListener {
                onPersonItemClick.invoke(popularPerson.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return popularPersons.size
    }

}
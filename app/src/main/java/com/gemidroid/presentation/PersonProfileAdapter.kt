package com.gemidroid.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemidroid.data.model.Images
import com.gemidroid.moviesdb.R
import com.gemidroid.util.Constants
import kotlinx.android.synthetic.main.person_profile_item.view.*


class PersonProfileAdapter(
    private val imagesProfiles: List<Images>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rootView = inflater.inflate(R.layout.person_profile_item, parent, false)
        return object : RecyclerView.ViewHolder(rootView){}
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindingItems(position, holder)
    }

    private fun bindingItems(
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        val imageProfile = imagesProfiles.get(position)
        val imageFullPath = "${Constants.IMAGE_PATH}${imageProfile.imagePath}"
        holder.itemView.apply {
            if (imageProfile.imagePath != null)
                Glide.with(context)
                    .load(imageFullPath)
                    .error(R.drawable.ic_launcher_background)
                    .into(img_profile)
            setOnClickListener {
                onItemClick.invoke(imageFullPath)
            }
        }
    }

    override fun getItemCount(): Int {
        return imagesProfiles.size
    }
}

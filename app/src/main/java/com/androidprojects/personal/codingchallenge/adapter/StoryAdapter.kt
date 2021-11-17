package com.androidprojects.personal.codingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidprojects.personal.codingchallenge.data.Story
import com.androidprojects.personal.codingchallenge.databinding.StoryListItemBinding
import com.androidprojects.personal.codingchallenge.util.GlideApp


/*
* Creation of adapter for display the data in a list view using Recycler-View component
* */
class StoryAdapter: ListAdapter<Story, StoryAdapter.StoryViewHolder>(StoryComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = StoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem!=null){
            holder.bind(currentItem)
        }


    }

    class StoryViewHolder(private val binder: StoryListItemBinding): RecyclerView.ViewHolder(binder.root){

        fun bind(story: Story){
         binder.apply {
             GlideApp.with(itemView)
                 .load(story.cover)
                 .into(coverImageLogo)


             authorName.text = story.user.name
             storyTitle.text = story.title
         }
       }
    }

    class StoryComparator : DiffUtil.ItemCallback<Story>(){
        override fun areItemsTheSame(oldItem: Story, newItem: Story) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Story, newItem: Story) =
             oldItem == newItem


    }
}
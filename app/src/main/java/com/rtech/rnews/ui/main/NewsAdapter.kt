package com.rtech.rnews.ui.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rtech.rnews.databinding.NewslayoutBinding
import com.rtech.rnews.network.Article
import com.rtech.rnews.ui.DetailsActivity

class NewsAdapter(val clickListener: OnClickListener): ListAdapter<Article,NewsAdapter.NewsViewHolder>(NewsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(clickListener,getItem(position))
    }


    class NewsViewHolder private constructor( val binding: NewslayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: OnClickListener,art:Article){
            binding.article=art
            binding.clicklistener=clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):NewsViewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding=NewslayoutBinding.inflate(layoutInflater,parent,false)
                return NewsViewHolder(binding)
            }
        }
    }

    class NewsDiffCallBack:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title==newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    class OnClickListener(val clickListener:(url: String)->Unit) {
        fun onClick(article: Article) =clickListener(article.url.toString())
    }
}
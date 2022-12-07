package com.example.myapplication.ui.news.home

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.ui.news.data.NewsArticle

class NewsFeedAdapter : ListAdapter<NewsArticle,NewsArticleViewHolder>(){

}
class NewsFeedDiffcallback: DiffUtil.ItemCallback<NewsArticle>(){
    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}
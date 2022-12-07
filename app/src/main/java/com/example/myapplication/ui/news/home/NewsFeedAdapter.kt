package com.example.myapplication.ui.news.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.news.data.NewsArticle

class NewsFeedAdapter() : ListAdapter<NewsArticle, NewsFeedAdapter.NewsArticleViewHolder>(NewsFeedDiffcallback()){
    class NewsArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsArticleViewHolder(inflater.inflate(R.layout.news_item,parent,false))
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {

    }

}
class NewsFeedDiffcallback: DiffUtil.ItemCallback<NewsArticle>(){
    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}
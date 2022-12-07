package com.example.myapplication.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentNewsBinding
import com.example.myapplication.ui.news.data.NewsArticle
import com.example.myapplication.ui.news.home.NewsFeedAdapter

class NewsFragment : Fragment() {

    private lateinit var NewsAdapter : NewsFeedAdapter
    lateinit var binding: FragmentNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,



    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NewsAdapter = NewsFeedAdapter()
            initNewsFeed()
            val newList = mutableListOf<NewsArticle>()
        for (i in 0..40){
            newList.add(
                NewsArticle("Google cloud releases Security Manager ",
                "blaslcaldvblablablablablablablabalbalbalbalb","https://androidtop.net/uploads/posts/2019-09/5d7f1295732de_1.jpg","5 minute ago")
            )
            NewsAdapter.submitList(newList)
        }
        }
    private fun initNewsFeed() = with(binding){
        newsFeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsAdapter

        }
    }
    companion object {
        fun newInstance() = NewsFragment()

    }
}
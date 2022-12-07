package com.example.myapplication.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.ui.news.home.NewsFeedAdapter

class NewsFragment : Fragment() {

    lateinit var newsAdapter: NewsFeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    companion object {
        fun newInstance() = NewsFragment()

    }
}
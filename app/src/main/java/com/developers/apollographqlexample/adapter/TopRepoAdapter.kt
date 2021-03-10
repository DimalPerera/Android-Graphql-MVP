package com.developers.apollographqlexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developers.apollographqlexample.R
import kotlinx.android.synthetic.main.row_repository.view.*

class TopRepoAdapter(var topRepositories: UserDetailQuery.TopRepositories?)  : RecyclerView.Adapter<TopRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRepoAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_top_repository, parent, false))
    }

    override fun onBindViewHolder(holder: TopRepoAdapter.ViewHolder, position: Int) {
        var launch  = topRepositories?.nodes()?.get(position)

        holder?.tvNameWithOwner.text = launch?.name()
        holder?.tvRepoName.text = launch?.nameWithOwner()
        holder?.tvRepoDetail.text = launch?.name()

        holder?.tvStarCount.text = launch?.stargazerCount().toString()
        holder?.tvRepoLanguage.text = launch?.primaryLanguage()?.name()
    }

    override fun getItemCount(): Int {
        return topRepositories?.nodes()?.size!!
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvNameWithOwner: TextView = view.repo_row_tv_name
        var tvRepoName: TextView = view.repo_row_tv_repoName
        var tvRepoDetail: TextView = view.repo_row_tv_repoDetail

        var tvStarCount: TextView = view.repo_row_tv_starCount
        var tvRepoLanguage: TextView = view.repo_row_tv_repoLanguage
    }

}
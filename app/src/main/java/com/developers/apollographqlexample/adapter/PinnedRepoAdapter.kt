package com.developers.apollographqlexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import coil.transform.CircleCropTransformation
import com.developers.apollographqlexample.R
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.row_repository.view.*
import org.jetbrains.anko.image

class PinnedRepoAdapter(var pinnedRepositories: UserDetailQuery.Repositories?)  : RecyclerView.Adapter<PinnedRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedRepoAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_repository, parent, false))
    }

    override fun onBindViewHolder(holder: PinnedRepoAdapter.ViewHolder, position: Int) {
        var launch  = pinnedRepositories?.nodes()?.get(position)

        holder?.tvNameWithOwner.text = launch?.name()
        holder?.tvRepoName.text = launch?.nameWithOwner()
        holder?.tvRepoDetail.text = launch?.name()

        holder?.tvStarCount.text = launch?.stargazerCount().toString()
        holder?.tvRepoLanguage.text = launch?.primaryLanguage()?.name()
    }

    override fun getItemCount(): Int {
        return pinnedRepositories?.nodes()?.size!!
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgAvater: ImageView = view.repo_row_img_avater
        var tvNameWithOwner: TextView = view.repo_row_tv_name
        var tvRepoName: TextView = view.repo_row_tv_repoName
        var tvRepoDetail: TextView = view.repo_row_tv_repoDetail

        var tvStarCount: TextView = view.repo_row_tv_starCount
        var tvRepoLanguage: TextView = view.repo_row_tv_repoLanguage
    }

}
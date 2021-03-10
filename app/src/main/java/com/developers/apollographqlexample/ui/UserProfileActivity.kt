package com.developers.apollographqlexample.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.developers.apollographqlexample.R
import com.developers.apollographqlexample.adapter.PinnedRepoAdapter
import com.developers.apollographqlexample.adapter.StarredRepoAdapter
import com.developers.apollographqlexample.adapter.TopRepoAdapter
import com.developers.apollographqlexample.contract.ContractInterface
import com.developers.apollographqlexample.databinding.ActivityUserProfileBinding
import com.developers.apollographqlexample.presenter.UserProfilePresenter
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.util.logging.Logger

class UserProfileActivity : AppCompatActivity(), ContractInterface.View {

    private lateinit var binding: ActivityUserProfileBinding

    private var presenter: UserProfilePresenter? = null

    companion object {
        val Log = Logger.getLogger(UserProfileActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = UserProfilePresenter(this)
        presenter?.networkCall()

    }

    override fun updateViewData() {

        runOnUiThread {

            val response = presenter?.showUserData()?.user()
            val repositories = presenter?.showUserData()?.user()?.repositories()
            val topRepositories = presenter?.showUserData()?.user()?.topRepositories()
            val starredRepositories = presenter?.showUserData()?.user()?.starredRepositories()

            img_userprofile.load(response?.avatarUrl().toString()) {
                crossfade(true)
                placeholder(R.drawable.github_icon)
                transformations(CircleCropTransformation())
            }

            tvName.text = response?.name()
            tvUsername.text = response?.login()
            tvEmail.text = response?.email()
            tvFollowing.text = String.format(getString(R.string.following_text),
                    response?.following()?.totalCount().toString())
            tvFollwers.text = String.format(getString(R.string.followers_text),
                    response?.followers()?.totalCount().toString())


            val adapter = PinnedRepoAdapter(repositories)

            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rvRepo.layoutManager = linearLayoutManager

            binding.rvRepo.adapter = adapter

            val topRepositoriesAdapter = TopRepoAdapter(topRepositories)

            val lmTopRepo = LinearLayoutManager(applicationContext)
            lmTopRepo.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvToprepositories.layoutManager = lmTopRepo

            binding.rvToprepositories.adapter = topRepositoriesAdapter

            val starredRepositoriesAdapter = StarredRepoAdapter(starredRepositories)

            val lmStarredRepo = LinearLayoutManager(applicationContext)
            lmStarredRepo.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvStarredRepositories.layoutManager = lmStarredRepo

            binding.rvStarredRepositories.adapter = starredRepositoriesAdapter
        }
    }

    override fun showProgress() {
        progressBar2.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar2.visibility = View.INVISIBLE

    }

    override fun showErrorMessage() {
        TODO("Not yet implemented")
    }

}
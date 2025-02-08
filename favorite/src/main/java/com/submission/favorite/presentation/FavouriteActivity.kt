package com.submission.favorite.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.core.ui.JobAdapter
import com.submission.expert.presentation.detail.DetailActivity
import com.submission.favorite.databinding.ActivityFavoriteBinding
import com.submission.favorite.di.favouriteModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private val jobAdapter: JobAdapter = JobAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favouriteModule)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        binding.topbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setContentView(binding.root)
        observeFavoriteJob()
    }

    private fun observeFavoriteJob() {
        lifecycleScope.launch {
            favouriteViewModel.favJob.collect { job ->
                binding.rvFavourite.apply {
                    adapter = jobAdapter
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    jobAdapter.setData(job)
                }
            }
        }
        getJobDetail()
    }

    private fun getJobDetail() {
        jobAdapter.setOnItemClickCallback(object : JobAdapter.OnItemClickCallback {
            override fun onItemClicked(id: Int) {
                val intent = Intent(this@FavouriteActivity, DetailActivity::class.java)
                intent.putExtra("id", id.toString())
                startActivity(intent)
            }
        })
    }
}
package com.submission.expert.presentation.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.submission.core.data.source.remote.Resource
import com.submission.core.ui.JobAdapter
import com.submission.expert.databinding.ActivityHomeBinding
import com.submission.expert.databinding.ActivitySplashBinding
import com.submission.expert.presentation.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var splash: ActivitySplashBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private val jobAdapter: JobAdapter = JobAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        splash = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splash.root)

        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(binding.root)
            observeJobs()
            getJobDetail()
            goToFavorite()
        }, 3000)
    }

    private fun observeJobs() {
        lifecycleScope.launch {
            homeViewModel.jobList.collect { job ->
                job.let {
                    when (job) {
                        is Resource.Loading -> setVisibility(true)
                        is Resource.Error -> setVisibility(false)
                        is Resource.Success -> {
                            binding.rvJob.apply {
                                adapter = jobAdapter
                                layoutManager = LinearLayoutManager(context)
                                jobAdapter.setData(job.data!!)
                            }
                            setVisibility(false)
                        }
                    }
                }
            }
        }
    }

    private fun getJobDetail() {
        jobAdapter.setOnItemClickCallback(object : JobAdapter.OnItemClickCallback {
            override fun onItemClicked(id: Int) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra("id", id.toString())
                startActivity(intent)
            }
        })
    }

    private fun goToFavorite() {
        binding.topbar.icFavourite.setOnClickListener {
            val splitInstallManager = SplitInstallManagerFactory.create(this@HomeActivity)

            if (splitInstallManager.installedModules.contains("favorite")) {
                println("Module sudah di install")
                startFavoriteActivity()
                return@setOnClickListener
            }

            val request = SplitInstallRequest.newBuilder()
                .addModule("favorite")
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    println("Module installed successfully")
                    startFavoriteActivity()
                }
                .addOnFailureListener { exception ->
                    println("Install failed: ${exception.message}")
                    try {
                        startFavoriteActivity()
                    } catch (e: Exception) {
                        println("Navigation failed: ${e.message}")
                    }
                }
        }
    }

    private fun startFavoriteActivity() {
        try {
            val intent = Intent().setClassName(
                this,
                "com.submission.favorite.presentation.FavouriteActivity"
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            println("FavoriteActivity not found: ${e.message}")
        }
    }

    private fun setVisibility(state: Boolean) {
        with(binding) {
            loader.item.visibility = if (state) View.VISIBLE else View.GONE
            rvJob.visibility = if (state) View.GONE else View.VISIBLE
            loader.item.run {
                if (state) playAnimation() else cancelAnimation()
            }
        }
    }
}
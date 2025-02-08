package com.submission.expert.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.submission.core.data.source.remote.Resource
import com.submission.core.domain.model.Jobs
import com.submission.core.utils.formatExperience
import com.submission.core.utils.formatJobType
import com.submission.expert.R
import com.submission.expert.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private var status = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        getJobDetail()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getJobDetail() {
        val id = intent.getStringExtra("id") ?: return
        detailViewModel.getJob(id.toInt())
        setVisibility(false)
        lifecycleScope.launch {
            detailViewModel.jobData.collect { job ->
                job?.let {
                    val jobData = job.data!!
                    when (job) {
                        is Resource.Success -> setUpContent(jobData)
                        is Resource.Error -> setVisibility(false)
                        is Resource.Loading -> setVisibility(false)
                    }
                }
            }
        }
    }

    private fun setVisibility(state: Boolean) {
        val visibility = if (state) View.VISIBLE else View.GONE
        val loaderVisibility = if (state) View.GONE else View.VISIBLE
        with(viewBinding) {
            toopbar.visibility = visibility
            svData.visibility = visibility
            layoutBtn.visibility = visibility
            loader.visibility = loaderVisibility
            loader.run {
                if (state) playAnimation() else cancelAnimation()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpContent(job: Jobs) {
        isFavourite(job.id)
        val uri = getString(R.string.uriJob, job.companySlug, job.slug, job.id.toString())
        viewBinding.run {
            toopbar.title = job.title
            Glide.with(this@DetailActivity)
                .load(job.companyLogo)
                .error(getString(com.submission.core.R.string.imgSrc, job.companyLogo))
                .into(imgJobs)
            companyName.text = job.companyName
            location.text = job.location
            experience.text = job.minimumTalentExperience.formatExperience()
            type.text = job.jobType.formatJobType()
            description.text = Html.fromHtml(job.description, Html.FROM_HTML_MODE_COMPACT)
            toopbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            lamar.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
            }
            addToFavorite.apply {
                setOnClickListener {
                    status = !status
                    detailViewModel.setFavJob(job, status)
                }
                text = when (!status) {
                    true -> context.getString(R.string.addFavorite)
                    else -> context.getString(R.string.haveSave)
                }
            }
        }
        setVisibility(true)
    }

    private fun isFavourite(id: Int) {
        lifecycleScope.launch {
            detailViewModel.favoriteJob.collect { job ->
                status = job.any { it.id == id }
                viewBinding.addToFavorite.text = when (!status) {
                    true -> this@DetailActivity.getString(R.string.addFavorite)
                    else -> this@DetailActivity.getString(R.string.haveSave)
                }
            }
        }
    }

}
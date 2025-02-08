package com.submission.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.core.R
import com.submission.core.databinding.ItemJobBinding
import com.submission.core.domain.model.Jobs
import com.submission.core.utils.formatExperience
import com.submission.core.utils.formatJobType

class JobAdapter : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    private var data: List<Jobs> = listOf()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(id: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Jobs) {
            binding.apply {
                val img = binding.root.context.getString(R.string.imgSrc, data.companyLogo)
                Glide.with(imgJobs)
                    .load(img)
                    .error(R.drawable.load_image)
                    .into(imgJobs)
                title.text = data.title
                companyName.text = data.companyName
                location.text = data.location
                experience.text = data.minimumTalentExperience.formatExperience()
                type.text = data.jobType.formatJobType()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemJobBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(data[position].id)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Jobs>) {
        this.data = data
        notifyDataSetChanged()
    }
}
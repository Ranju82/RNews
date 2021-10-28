package com.rtech.rnews.utils

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rtech.rnews.R
import com.rtech.rnews.ui.main.NewsApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animations)
                    .error(R.drawable.broken_image))
            .into(imgView)
    }
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView: ImageView,status: NewsApiStatus?){
    when(status){
        NewsApiStatus.LOADING->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animations)
        }
        NewsApiStatus.ERROR->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.link_off)
        }
        NewsApiStatus.LOADING->{
            statusImageView.visibility = View.GONE
        }
    }
}
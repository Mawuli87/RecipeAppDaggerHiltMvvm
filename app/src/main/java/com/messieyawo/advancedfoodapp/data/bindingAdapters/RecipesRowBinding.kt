package com.messieyawo.advancedfoodapp.data.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.messieyawo.advancedfoodapp.R


class RecipesRowBinding {

    companion object {

        @BindingAdapter("loadImagesFromUrl")
        @JvmStatic
        fun loadImagesFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

//        @BindingAdapter("RecipesRowBinding ")
//        @JvmStatic
//        fun setNumberOfLikes(textView: TextView,likes:Int){
//            textView.text = likes.toString()
//        }
//
//        @BindingAdapter
//        @JvmStatic
//        fun setNumberOfMinutes(textView: TextView,minutes:Int){
//            textView.text = minutes.toString()
//        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            }
        }


    }
}
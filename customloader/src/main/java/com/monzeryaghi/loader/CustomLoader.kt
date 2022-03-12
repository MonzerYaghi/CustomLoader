package com.monzeryaghi.loader


import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.monzeryaghi.loader.databinding.LayoutLoaderBinding


/**
 * Created by Monzer Yaghi
 */
class CustomLoader(context: Context) {

    companion object {
        var lottieAnimationFileName = ""
        var indeterminateDrawable: Drawable? = null
        var animatedDrawableResource = -1
    }

    private var binding: LayoutLoaderBinding =
        LayoutLoaderBinding.inflate(LayoutInflater.from(context))

    private var frameAnimation: AnimationDrawable? = null

    private lateinit var dialog: Dialog

    init {
        createDialogLayout(context)
        initializeLoader()
    }

    private fun initializeLoader() {
        when {
            //Lottie Animation available
            lottieAnimationFileName.isNotEmpty() -> {
                binding.animationView.setAnimation(lottieAnimationFileName)
            }
            //Usually a drawable containing an array of images to be animated
            animatedDrawableResource != -1 -> {
                binding.imageViewLoader.setBackgroundResource(animatedDrawableResource)
            }
            //Native Loader with indeterminate drawable
            indeterminateDrawable != null -> {
                binding.progressBarLoader.indeterminateDrawable = indeterminateDrawable
            }
        }
    }

    private fun createDialogLayout(context: Context) {

        //Setting the loader background as transparent
        dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        //Setting loader layout
        dialog.setContentView(binding.root)

        //Setting the loader layout to fullscreen
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    fun showLoader(loaderMessage: String? = "") {

        if (!dialog.isShowing) {
            dialog.show()
        }

        when {
            //Lottie Animation available
            lottieAnimationFileName.isNotEmpty() -> {
                binding.animationView.visibility = View.VISIBLE
                binding.animationView.playAnimation()
            }
            //Usually a drawable containing an array of images to be animated
            animatedDrawableResource != -1 -> {
                binding.imageViewLoader.visibility = View.VISIBLE
                frameAnimation = binding.imageViewLoader.background as AnimationDrawable?
                frameAnimation?.start()
            }
            //Native Loader
            else -> {
                binding.progressBarLoader.visibility = View.VISIBLE
            }
        }

        //Handling loader message
        if (loaderMessage.isNullOrEmpty()) {
            binding.textViewLoader.visibility = View.GONE
        } else {
            binding.textViewLoader.text = loaderMessage
            binding.textViewLoader.visibility = View.VISIBLE
        }
    }

    fun hide() {

        //Stop frame animation if available
        stopFrameAnimation()

        //Stop lottie animation if available
        stopLottieAnimation()

        //Hide all views
        hideViews()

        //Closing dialog
        if (isLoaderShowing()) {
            dialog.dismiss()
        }
    }

    private fun hideViews() {
        binding.animationView.visibility = View.GONE
        binding.textViewLoader.visibility = View.GONE
        binding.imageViewLoader.visibility = View.GONE
        binding.progressBarLoader.visibility = View.GONE
    }

    private fun stopLottieAnimation() {
        if (binding.animationView.isAnimating) {
            binding.animationView.cancelAnimation()
        }
    }

    private fun stopFrameAnimation() {
        if (frameAnimation != null) {
            frameAnimation?.stop()
            frameAnimation = null
        }
    }

    fun setCancelable(isCancelable: Boolean) {
        dialog.setCancelable(isCancelable)
    }

    private fun isLoaderShowing(): Boolean {
        return dialog.isShowing
    }

}
package com.bentley.imagesearch.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.bentley.imagesearch.databinding.ActivityDetailBinding
import com.bentley.imagesearch.utils.formatDateTime
import com.bentley.imagesearch.utils.viewBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding(ActivityDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        closeActivity()
    }

    private fun setupViews() {
        val imageUrl = intent.getStringExtra(ARGS_KEY1)
        val siteName = intent.getStringExtra(ARGS_KEY2)
        val dateTime = intent.getStringExtra(ARGS_KEY3)

        binding.apply {
            ivImage.load(imageUrl)
            tvSiteName.text = "출처: $siteName"
            dateTime?.formatDateTime().also { tvDatetime.text = "작성시간: $it" }
        }
    }


    private fun closeActivity() {
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    companion object {
        private const val ARGS_KEY1 = "imageUrl"
        private const val ARGS_KEY2 = "siteName"
        private const val ARGS_KEY3 = "datetime"
    }
}
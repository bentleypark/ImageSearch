package com.bentley.imagesearch.presentation.detail

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import coil.load
import com.bentley.imagesearch.R
import com.bentley.imagesearch.databinding.FragmentDetailBinding
import com.bentley.imagesearch.presentation.base.BaseFragment
import com.bentley.imagesearch.utils.formatDateTime
@RequiresApi(Build.VERSION_CODES.O)
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_detailFragment_pop)
                }
            })

        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            val imgUrl = arguments?.getString(ARGS_KEY1)
            if (imgUrl != null) {
                ivImage.load(imgUrl)
            }

            val siteName = arguments?.getString(ARGS_KEY2)
            if (siteName != null) {
                tvSiteName.text = "출처: $siteName"
            }

            val datetime = arguments?.getString(ARGS_KEY3)
            if (siteName != null) {
                tvDatetime.text = "작성시간: ${datetime?.formatDateTime()}"
            }

            btnClose.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_pop)
            }
        }
    }

    companion object {
        private const val ARGS_KEY1 = "imageUrl"
        private const val ARGS_KEY2 = "siteName"
        private const val ARGS_KEY3 = "dateTime"
    }
}
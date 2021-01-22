package com.bentley.imagesearch.presentation.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bentley.imagesearch.utils.viewLifecycle

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected var binding: T by viewLifecycle()
}
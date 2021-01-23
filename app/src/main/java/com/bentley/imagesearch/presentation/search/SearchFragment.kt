package com.bentley.imagesearch.presentation.search

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bentley.imagesearch.R
import com.bentley.imagesearch.databinding.FragmentSearchBinding
import com.bentley.imagesearch.presentation.base.BaseFragment
import com.bentley.imagesearch.utils.*
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchListAdapter: SearchListAdapter
    private var searchJob: Job? = null
    private var isLastPage = false
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObserve()
    }

    private fun setupView() {
        binding.apply {

            searchListAdapter = SearchListAdapter(mutableListOf())

            searchList.apply {
                adapter = searchListAdapter
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 3).apply {
                    orientation = GridLayoutManager.VERTICAL
                }

                addOnScrollListener(object :
                    PaginationScrollListener(this.layoutManager as GridLayoutManager) {
                    override fun isLastPage(): Boolean {
                        return isLastPage
                    }

                    override fun isLoading(): Boolean {
                        return isLoading
                    }

                    override fun loadMoreItems() {
                        isLoading = true
                        viewModel.fetchNextPage()
                    }
                })
            }

            btnSearch.setOnClickListener {
                performSearch()
            }

            etSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        performSearch()
                    }
                    true
                }

                textChanges()
                    .debounce(1_000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        if (it.toString().isNotEmpty()) {
                            performSearch()
                        }
                    }, { throwable -> Timber.e(throwable) })
            }
        }
    }

    private fun setupObserve() {
        binding.apply {
            viewModel.apply {
                searchResult.observe(viewLifecycleOwner, { result ->
                    progressCircular.makeGone()
                    if (result.isNotEmpty()) {
                        ivDefaultImg.makeGone()
                        searchList.makeVisible()

                        searchListAdapter.addAll(result)
                    } else {
                        tvNoResult.makeVisible()
                    }
                })

                nextList.observe(viewLifecycleOwner, { result ->
                    if (result.isNotEmpty()) {
                        searchListAdapter.add(result)
                        isLoading = false
                    } else {
                        isLastPage = true
                    }
                })
            }
        }
    }

    private fun performSearch() {
        binding.apply {
            val query = SpannableStringBuilder(etSearch.text).toString().trim()
            if (query.isNotEmpty()) {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {

                    progressCircular.makeVisible()
                    ivDefaultImg.makeGone()
                    tvNoResult.makeGone()
                    searchList.makeGone()

                    etSearch.apply {
                        clearFocus()
                        hideKeyboard()
                    }
                    viewModel.search(query)
                }
            } else {
                makeToast(getString(R.string.no_search_query))
            }
        }
    }

}
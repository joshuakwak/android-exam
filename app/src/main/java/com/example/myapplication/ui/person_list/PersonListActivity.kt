package com.example.myapplication.ui.person_list

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.util.collectLatestData
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse
import com.example.myapplication.databinding.ActivityPersonListBinding
import com.example.myapplication.ui.person_view.PersonViewActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PersonListActivity : BaseActivity<PersonListViewModel, ActivityPersonListBinding>(),
PersonListAdapter.PersonListAdapterListener {
    override val layoutId = R.layout.activity_person_list
    override val viewModel: PersonListViewModel by viewModels()

    private lateinit var adapter: PersonListAdapter

    override fun onItemClick(item: PersonDetailsEntity) {
        val intent = Intent(this, PersonViewActivity::class.java)
        intent.putExtra("personDetails", Gson().toJson(item))
        startActivity(intent)
    }

    override fun initViews() {
        super.initViews()
        hideSystemUI()
        initSwipeRefreshListener()
    }

    override fun subscribe() {
        super.subscribe()
        viewModel.listItems.collectLatestData(lifecycleScope) { items ->
            initAdapter(items)
        }

        viewModel.isLoading.collectLatestData(lifecycleScope) { result ->
            if (!result) {
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun initAdapter(personList: List<PersonDetailsEntity>) {
        adapter = PersonListAdapter(personList, this)
        binding.recyclerView.adapter = adapter
    }

    private fun initSwipeRefreshListener() = with(binding) {
        swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.getPersonList()
                viewModel.isLoading.collectLatestData(lifecycleScope) {
                    isRefreshing = it
                }
                viewModel.listItems.collectLatestData(lifecycleScope) { items ->
                    initAdapter(items)
                }
            }
        }
    }
}
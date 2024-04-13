package com.example.myapplication.ui.person_view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseActivity
import com.example.myapplication.common.util.StringExtensions.toStandardDate
import com.example.myapplication.common.util.collectLatestData
import com.example.myapplication.common.util.setImageFromURL
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.databinding.ActivityPersonViewBinding
import com.example.myapplication.ui.person_list.PersonListAdapter
import com.example.myapplication.ui.person_list.PersonListViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Locale

@AndroidEntryPoint
class PersonViewActivity : BaseActivity<PersonViewViewModel, ActivityPersonViewBinding>() {
    override val layoutId = R.layout.activity_person_view
    override val viewModel: PersonViewViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        hideSystemUI()
        initPersonDetailsView()
    }

    @SuppressLint("SetTextI18n")
    private fun initPersonDetailsView() = with (binding) {
        val details = Gson().fromJson(
            intent.getStringExtra("personDetails"),
            PersonDetailsEntity::class.java
        )

        Timber.d(details.toString())

        constraintLayoutToolbarBack.setOnClickListener { finish() }

        imageViewPerson.setImageFromURL(details.picture, progressBarImage)
        textViewDetailsName.text = details.name
        textViewDetailsEmail.text = details.email
        textViewGender.text = details.gender?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        textViewBirthdate.text = details.dob?.toStandardDate()
        textViewAge.text = details.age.toString()
        textViewMobile.text = details.phone
        textViewAddress.text = details.location
    }
}
package com.gemidroid.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.gemidroid.moviesdb.R
import com.gemidroid.util.Constants
import kotlinx.android.synthetic.main.fragment_person_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class PersonDetailsFragment : Fragment() {

    private val personDetailsViewModel by viewModel<PersonDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(Constants.PERSON_ID).let {
            personDetailsViewModel.getPersonDetails(it!!)
        }

        personDetailsViewModel.personDetails.observe(viewLifecycleOwner, { personDetails ->
            toggleData(true)
            personDetails.apply {
                detail_toolbar.title = name
                Glide.with(requireContext())
                    .load("${Constants.IMAGE_PATH}${imagePath}")
                    .error(R.drawable.ic_launcher_foreground)
                    .into(img_profile)
                if (biography.isEmpty()) {
                    txt_bio_title.visibility = View.GONE
                    txt_bio_data.visibility = View.GONE
                } else txt_bio_data.text = biography
                txt_birthday_data.text = birthDay
                if (nickNames.isNullOrEmpty()) {
                    txt_known_for_title.visibility = View.GONE
                    txt_known_for_data.visibility = View.GONE
                } else {
                    val builder = mutableListOf<String>()
                    nickNames.forEach {
                        builder.add(it)
                    }
                    txt_known_for_data.text = builder.joinToString()
                }
                txt_date_birth_data.text = dateOfBirth
                txt_department_data.text = department
            }
        })

        personDetailsViewModel.personImages.observe(viewLifecycleOwner, {
            rec_profiles.apply {
                isNestedScrollingEnabled = false
                layoutManager =
                    GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
                adapter = PersonProfileAdapter(it.personImages) { imagePath ->
                    val intent = Intent(requireActivity(), FullScreenProfileActivity::class.java)
                    intent.putExtra(Constants.IMAGE_KEY, imagePath)
                    startActivity(intent)
                }
            }
        })

        personDetailsViewModel.error.observe(viewLifecycleOwner, {
            val error = if (it is UnknownHostException)
                getString(R.string.no_internet_connection)
            else getString(R.string.general_issue)
            txt_error.text = error
            toggleData(false)

        })

        img_back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun toggleData(isNoError: Boolean) {
        if (isNoError) {
            nested_data.visibility = View.VISIBLE
            txt_error.visibility = View.GONE
        } else {
            nested_data.visibility = View.GONE
            txt_error.visibility = View.VISIBLE
        }
    }
}
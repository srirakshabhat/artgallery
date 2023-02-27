package com.sriraksha.artsgallery.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.databinding.FragmentArtDetailsBinding
import com.sriraksha.artsgallery.util.Resource
import com.sriraksha.artsgallery.util.loadProfilePhoto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtDetailsFragment : Fragment() {

    private lateinit var fragmentArtDetailsBinding: FragmentArtDetailsBinding
    private val viewModel: ArtDetailsViewModel by viewModels()
    private val artDetailsAdapter = ArtDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentArtDetailsBinding = FragmentArtDetailsBinding.inflate(inflater, container, false)
        return fragmentArtDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArtDetails(arguments?.getString("objectId").toString())
        populateArtDetails()
    }

    private fun populateArtDetails() {
        viewModel.artDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { it1 -> displayArtDetails(it1) }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayArtDetails(artDetails: ArtDetails) {
        fragmentArtDetailsBinding.artName.text = "Art name : ${artDetails.objectID}"
        fragmentArtDetailsBinding.title.text = "Title : ${artDetails.title}"
        fragmentArtDetailsBinding.department.text = "Department : ${artDetails.department}"
        fragmentArtDetailsBinding.artImg.loadProfilePhoto(artDetails.primaryImage,requireContext())
        artDetailsAdapter.submitList(artDetails.additionalImages)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        fragmentArtDetailsBinding.galleryRecyclerview.apply {
            adapter = artDetailsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun showProgressBar() {
        (activity as ArtsGalleryActivity).showProgressBar()
    }

    private fun hideProgressBar() {
        (activity as ArtsGalleryActivity).hideProgressBar()
    }
}

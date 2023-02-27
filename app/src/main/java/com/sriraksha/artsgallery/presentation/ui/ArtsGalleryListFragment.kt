package com.sriraksha.artsgallery.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriraksha.artsgallery.R
import com.sriraksha.artsgallery.databinding.FragmentArtsgalleryListBinding
import com.sriraksha.artsgallery.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsGalleryListFragment : Fragment(), ArtGalleryListAdapter.OnArtItemClick {

    private val viewModel: ArtsGalleryListViewModel by viewModels()
    private lateinit var fragmentArtsGalleryBinding: FragmentArtsgalleryListBinding
    private val artGalleryListAdapter = ArtGalleryListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getArtList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentArtsGalleryBinding = FragmentArtsgalleryListBinding.inflate(inflater, container, false)
        return fragmentArtsGalleryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateArts()
        searchArts()
    }

    private fun searchArts() {
        fragmentArtsGalleryBinding.searchView.setIconifiedByDefault(false)
        fragmentArtsGalleryBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getSearchedArtList(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    viewModel.getArtList()
                }
                return false
            }
        })
    }

    private fun initRecyclerView() {
        fragmentArtsGalleryBinding.artlistRecyclerview.apply {
            adapter = artGalleryListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun populateArts() {
        viewModel.artListLiveData.observe(viewLifecycleOwner) {
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
                    artGalleryListAdapter.submitList(null)
                    it.data?.objectIDs?.let { it1 -> displayArtsData(it1) }
                }
            }
        }
    }

    private fun displayArtsData(artData: List<Int>) {
        artGalleryListAdapter.submitList(artData.toList())
        initRecyclerView()
    }

    private fun showProgressBar() {
        (activity as ArtsGalleryActivity).showProgressBar()
    }

    private fun hideProgressBar() {
        (activity as ArtsGalleryActivity).hideProgressBar()
    }

    override fun onArtItemClick(objectId: String) {
        val bundle = bundleOf("objectId" to objectId)
        view?.findNavController()?.navigate(R.id.artDetailsFragment, bundle)
    }
}

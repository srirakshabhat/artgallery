package com.sriraksha.artsgallery.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.domain.usecase.GetArtListUseCase
import com.sriraksha.artsgallery.domain.usecase.GetSearchedArtListUseCase
import com.sriraksha.artsgallery.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsGalleryListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase,
    private val getSearchedArtListUseCase: GetSearchedArtListUseCase,
) : ViewModel() {

    private val _artListLiveData = MutableLiveData<Resource<ArtList>>()
    val artListLiveData: LiveData<Resource<ArtList>> = _artListLiveData

    fun getArtList() {
        _artListLiveData.postValue(Resource.Loading)
        viewModelScope.launch {
            val response = getArtListUseCase.execute()
            if (response.data == null) {
                _artListLiveData.postValue(Resource.Error("Something went wrong"))
            } else {
                _artListLiveData.postValue(Resource.Success(response.data))
            }
        }
    }

    fun getSearchedArtList(searchQuery: String) {
        _artListLiveData.postValue(Resource.Loading)
        viewModelScope.launch {
            val response = getSearchedArtListUseCase.execute(searchQuery)
            if (response.data == null) {
                _artListLiveData.postValue(Resource.Error("Something went wrong"))
            } else {
                _artListLiveData.postValue(Resource.Success(response.data))
            }
        }
    }
}

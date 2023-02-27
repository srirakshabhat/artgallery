package com.sriraksha.artsgallery.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.domain.usecase.GetArtDetailsUseCase
import com.sriraksha.artsgallery.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val getArtDetailsUseCase: GetArtDetailsUseCase,
) : ViewModel() {

    private val _artDetailsLiveData = MutableLiveData<Resource<ArtDetails>>()
    val artDetailsLiveData: LiveData<Resource<ArtDetails>> = _artDetailsLiveData

    fun getArtDetails(objectId: String) {
        _artDetailsLiveData.postValue(Resource.Loading)
        viewModelScope.launch {
            val response = getArtDetailsUseCase.execute(objectId)
            if (response.data == null) {
                _artDetailsLiveData.postValue(Resource.Error("Something went wrong"))
            } else {
                _artDetailsLiveData.postValue(Resource.Success(response.data))
            }
        }
    }
}

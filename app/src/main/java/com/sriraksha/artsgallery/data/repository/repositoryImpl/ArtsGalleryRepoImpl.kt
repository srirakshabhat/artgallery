package com.sriraksha.artsgallery.data.repository.repositoryImpl

import android.util.Log
import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.data.repository.datasource.ArtsGalleryAPIService
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ArtsGalleryRepoImpl(
    private val artsGalleryAPIService: ArtsGalleryAPIService,
) : ArtsGalleryRepo {
    override suspend fun getArtList(): Resource<ArtList> {
        return withContext(Dispatchers.IO) {
            return@withContext responseToResource(artsGalleryAPIService.getArtList())
        }
    }

    override suspend fun getSearchedArtList(searchQuery: String): Resource<ArtList> {
        return withContext(Dispatchers.IO) {
            return@withContext responseToResource(artsGalleryAPIService.getSearchedArts(searchQuery))
        }
    }

    override suspend fun getArt(objectId: String): Resource<ArtDetails> {
        return withContext(Dispatchers.IO) {
            val response = artsGalleryAPIService.getArt(objectId)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    return@withContext Resource.Success(result)
                }
            }
            return@withContext Resource.Error(response.message())
        }
    }

    private fun responseToResource(response: Response<ArtList>): Resource<ArtList> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}

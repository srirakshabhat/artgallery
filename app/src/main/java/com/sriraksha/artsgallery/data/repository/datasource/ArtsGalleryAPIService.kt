package com.sriraksha.artsgallery.data.repository.datasource

import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.data.entity.ArtList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtsGalleryAPIService {
    @GET("v1/objects")
    suspend fun getArtList(): Response<ArtList>

    @GET("v1/search")
    suspend fun getSearchedArts(
        @Query("q")
        searchQuery: String,
    ): Response<ArtList>

    @GET("v1/objects/{objectID}")
    suspend fun getArt(
        @Path("objectID")
        objectId: String,
    ): Response<ArtDetails>
}

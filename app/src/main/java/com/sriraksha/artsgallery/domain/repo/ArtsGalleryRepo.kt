package com.sriraksha.artsgallery.domain.repo

import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.util.Resource

interface ArtsGalleryRepo {
    suspend fun getArtList(): Resource<ArtList>
    suspend fun getSearchedArtList(searchQuery: String): Resource<ArtList>
    suspend fun getArt(objectId: String): Resource<ArtDetails>
}

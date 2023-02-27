package com.sriraksha.artsgallery.domain.usecase

import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.util.Resource

class GetSearchedArtListUseCase(
    private val artsGalleryRepo: ArtsGalleryRepo,
) {
    suspend fun execute(searchQuery: String): Resource<ArtList> {
        return artsGalleryRepo.getSearchedArtList(searchQuery)
    }
}

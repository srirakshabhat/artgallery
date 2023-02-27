package com.sriraksha.artsgallery.domain.usecase

import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.util.Resource

class GetArtListUseCase(
    private val artsGalleryRepo: ArtsGalleryRepo,
) {
    suspend fun execute(): Resource<ArtList> {
        return artsGalleryRepo.getArtList()
    }
}

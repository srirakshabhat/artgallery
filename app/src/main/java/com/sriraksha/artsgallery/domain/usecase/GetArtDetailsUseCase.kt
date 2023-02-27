package com.sriraksha.artsgallery.domain.usecase

import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.util.Resource

class GetArtDetailsUseCase(
    private val artsGalleryRepo: ArtsGalleryRepo,
) {
    suspend fun execute(objectId: String): Resource<ArtDetails> {
        return artsGalleryRepo.getArt(objectId)
    }
}

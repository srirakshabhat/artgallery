package com.sriraksha.artsgallery.presentation.di

import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.domain.usecase.GetArtDetailsUseCase
import com.sriraksha.artsgallery.domain.usecase.GetArtListUseCase
import com.sriraksha.artsgallery.domain.usecase.GetSearchedArtListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun providesGetArtListUseCase(artsGalleryRepo: ArtsGalleryRepo): GetArtListUseCase {
        return GetArtListUseCase(artsGalleryRepo)
    }

    @Singleton
    @Provides
    fun providesGetSearchedArtListUseCase(artsGalleryRepo: ArtsGalleryRepo): GetSearchedArtListUseCase {
        return GetSearchedArtListUseCase(artsGalleryRepo)
    }

    @Singleton
    @Provides
    fun providesGetArtDetailsUseCase(artsGalleryRepo: ArtsGalleryRepo): GetArtDetailsUseCase {
        return GetArtDetailsUseCase(artsGalleryRepo)
    }
}

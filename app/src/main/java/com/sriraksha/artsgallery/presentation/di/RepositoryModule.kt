package com.sriraksha.artsgallery.presentation.di

import com.sriraksha.artsgallery.data.repository.datasource.ArtsGalleryAPIService
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.data.repository.repositoryImpl.ArtsGalleryRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesArtsGalleryRepo(artsGalleryAPIService: ArtsGalleryAPIService): ArtsGalleryRepo {
        return ArtsGalleryRepoImpl(artsGalleryAPIService)
    }
}

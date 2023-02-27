package com.sriraksha.artsgallery.domain.usecase

import com.sriraksha.artsgallery.data.entity.ArtList
import com.sriraksha.artsgallery.domain.repo.ArtsGalleryRepo
import com.sriraksha.artsgallery.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GetSearchedArtListUseCaseTest {
    @MockK(relaxUnitFun = true, relaxed = true)
    private lateinit var artsGalleryRepo: ArtsGalleryRepo

    private lateinit var getSearchedArtListUseCase: GetSearchedArtListUseCase

    private val searchQuery = "z"

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)

        getSearchedArtListUseCase = GetSearchedArtListUseCase(artsGalleryRepo)

        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `successfully fetches art list`() = runTest {
        coEvery {
            artsGalleryRepo.getSearchedArtList(searchQuery)
        } returns Resource.Success(ArtList(mutableListOf(1, 2), 1))

        val response = getSearchedArtListUseCase.execute(searchQuery)

        assertThat(response.data?.objectIDs).isEqualTo(mutableListOf(1, 2))
    }


    @Test
    fun `fails to fetch searched art list`() = runTest {
        coEvery {
            artsGalleryRepo.getSearchedArtList(searchQuery)
        } returns Resource.Error("Something went wrong")

        val response = getSearchedArtListUseCase.execute(searchQuery)

        assertThat(response.data).isNull()
        assertThat(response.message).isEqualTo("Something went wrong")
    }
}

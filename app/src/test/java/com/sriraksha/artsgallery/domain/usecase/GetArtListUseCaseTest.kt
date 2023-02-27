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

class GetArtListUseCaseTest {
    @MockK(relaxUnitFun = true, relaxed = true)
    private lateinit var artsGalleryRepo: ArtsGalleryRepo

    private lateinit var getArtListUseCase: GetArtListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)

        getArtListUseCase = GetArtListUseCase(artsGalleryRepo)

        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `successfully fetches art list`() = runTest {
        coEvery {
            artsGalleryRepo.getArtList()
        } returns Resource.Success(ArtList(mutableListOf(1, 2), 1))

        val response = getArtListUseCase.execute()

        assertThat(response.data?.objectIDs).isEqualTo(mutableListOf(1, 2))
    }

    @Test
    fun `fails to fetch art list`() = runTest {
        coEvery {
            artsGalleryRepo.getArtList()
        } returns Resource.Error("Something went wrong")

        val response = getArtListUseCase.execute()

        assertThat(response.data).isNull()
        assertThat(response.message).isEqualTo("Something went wrong")
    }
}

package com.sriraksha.artsgallery.domain.usecase

import com.sriraksha.artsgallery.data.entity.ArtDetails
import com.sriraksha.artsgallery.data.entity.Constituent
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

class GetArtDetailsUseCaseTest {
    @MockK(relaxUnitFun = true, relaxed = true)
    private lateinit var artsGalleryRepo: ArtsGalleryRepo

    private lateinit var getArtDetailsUseCase: GetArtDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)

        getArtDetailsUseCase = GetArtDetailsUseCase(artsGalleryRepo)

        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `successfully fetches art details`() = runTest {
        coEvery {
            artsGalleryRepo.getArt("1")
        } returns Resource.Success(
            ArtDetails(
                objectID = 1,
                isHighlight = false,
                additionalImages = mutableListOf(),
                constituents = mutableListOf(Constituent(constituentID = 1)),
                isPublicDomain = false,
                isTimelineWork = false,
                measurements = null,
                tags = null,
            ),
        )

        val response = getArtDetailsUseCase.execute("1")

        assertThat(response.data?.objectID).isEqualTo(1)
    }

    @Test
    fun `fails to fetch art details`() = runTest {
        coEvery {
            artsGalleryRepo.getArt("z")
        } returns Resource.Error("Something went wrong")

        val response = getArtDetailsUseCase.execute("z")

        assertThat(response.message).isEqualTo("Something went wrong")
    }
}

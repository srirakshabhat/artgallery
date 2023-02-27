package com.sriraksha.artsgallery.data.repositoryImpl

import com.sriraksha.artsgallery.BuildConfig
import com.sriraksha.artsgallery.data.repository.datasource.ArtsGalleryAPIService
import com.sriraksha.artsgallery.data.repository.repositoryImpl.ArtsGalleryRepoImpl
import com.sriraksha.artsgallery.getArtDetailsFailureResponse
import com.sriraksha.artsgallery.getArtDetailsSuccessResponse
import com.sriraksha.artsgallery.getArtListSuccessResponse
import com.sriraksha.artsgallery.getSearchedArtListFailureResponse
import com.sriraksha.artsgallery.getSearchedArtListSuccessResponse
import io.mockk.MockKAnnotations
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtsGalleryRepoImplTest {
    private val mockWebServer = MockWebServer()

    private lateinit var artsGalleryRepoImpl: ArtsGalleryRepoImpl

    private var artsGalleryAPIService: ArtsGalleryAPIService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(ArtsGalleryAPIService::class.java)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        artsGalleryRepoImpl = ArtsGalleryRepoImpl(artsGalleryAPIService)
    }

    @Test
    fun `successfully fetches art list`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getArtListSuccessResponse),
        )

        val response = artsGalleryRepoImpl.getArtList()

        assertThat(response.data?.total).isEqualTo(484269)
    }

    @Test
    fun `successfully fetches searched art list`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getSearchedArtListSuccessResponse),
        )

        val response = artsGalleryRepoImpl.getSearchedArtList("sunflowers")

        assertThat(response.data!!.objectIDs[0]).isEqualTo(436524)
        assertThat(response.data!!.total).isEqualTo(89)
    }

    @Test
    fun `fails to fetch searched art list due to empty search query`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getSearchedArtListFailureResponse),
        )
        val response = artsGalleryRepoImpl.getSearchedArtList("")

        assertThat(response.data?.objectIDs).isNull()
    }

    @Test
    fun `successfully fetches searched art details`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getArtDetailsSuccessResponse),
        )

        val response = artsGalleryRepoImpl.getArt("1")

        assertThat(response.data!!.objectID).isEqualTo(1)
        assertThat(response.data!!.isHighlight).isEqualTo(false)
        assertThat(response.data!!.accessionNumber).isEqualTo("1979.486.1")
    }

    @Test
    fun `fails to fetch object due to incorrect input`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody(getArtDetailsFailureResponse),
        )
        val response = artsGalleryRepoImpl.getArt("z")

        assertThat(response.data).isNull()
        assertThat(response.message).isEqualTo("Bad Request")
    }
}

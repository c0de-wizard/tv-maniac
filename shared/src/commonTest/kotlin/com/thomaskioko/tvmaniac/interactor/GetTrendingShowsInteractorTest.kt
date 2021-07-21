package com.thomaskioko.tvmaniac.interactor

import app.cash.turbine.test
import com.thomaskioko.tvmaniac.MockData.getTrendingDataMap
import com.thomaskioko.tvmaniac.MockData.getTvResponse
import com.thomaskioko.tvmaniac.datasource.enums.TimeWindow.DAY
import com.thomaskioko.tvmaniac.datasource.enums.TimeWindow.WEEK
import com.thomaskioko.tvmaniac.datasource.enums.TrendingDataRequest
import com.thomaskioko.tvmaniac.datasource.enums.TvShowCategory.TRENDING
import com.thomaskioko.tvmaniac.datasource.mapper.toTvShowEntity
import com.thomaskioko.tvmaniac.datasource.repository.tvshow.TvShowsRepository
import com.thomaskioko.tvmaniac.util.DomainResultState
import com.thomaskioko.tvmaniac.util.runBlocking
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.Test

internal class GetTrendingShowsInteractorTest {

    private val repository: TvShowsRepository = mockk()
    private val interactor = GetTrendingShowsInteractor(repository)

    @Test
    fun wheneverPopularShowsInteractorIsInvoked_ExpectedDataIsReturned() = runBlocking {
        coEvery { repository.getTrendingShows(DAY.window) } returns getTvResponse().results
            .map { it.toTvShowEntity() }
            .map { it.copy(
                showCategory = TRENDING,
                timeWindow = DAY
            ) }

        coEvery { repository.getTrendingShows(WEEK.window) } returns getTvResponse().results
            .map { it.toTvShowEntity() }
            .map { it.copy(
                showCategory = TRENDING,
                timeWindow = WEEK
            ) }

        val trendingTypes = listOf(
            TrendingDataRequest.TODAY,
            TrendingDataRequest.THIS_WEEK
        )

        interactor.invoke(trendingTypes).test {
            expectItem() shouldBe DomainResultState.Loading()
            expectItem() shouldBe DomainResultState.Success(getTrendingDataMap())
            expectComplete()
        }
    }

    @Test
    fun wheneverPopularShowsInteractorIsInvoked_ErrorIsReturned() = runBlocking {
        val trendingTypes = listOf(
            TrendingDataRequest.TODAY,
            TrendingDataRequest.THIS_WEEK
        )
        interactor.invoke(trendingTypes).test {
            expectItem() shouldBe DomainResultState.Loading()
            expectItem() shouldBe DomainResultState.Error("Something went wrong")
            expectComplete()
        }
    }
}
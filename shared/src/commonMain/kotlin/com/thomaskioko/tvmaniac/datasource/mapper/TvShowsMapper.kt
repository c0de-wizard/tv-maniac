package com.thomaskioko.tvmaniac.datasource.mapper

import com.thomaskioko.tvmaniac.datasource.cache.Tv_show
import com.thomaskioko.tvmaniac.datasource.cache.model.TvShowsEntity
import com.thomaskioko.tvmaniac.datasource.network.model.ShowResponse
import com.thomaskioko.tvmaniac.util.StringUtil.formatPosterPath

fun ShowResponse.toTvShowEntity(): TvShowsEntity {
    return TvShowsEntity(
        id = id,
        title = name,
        description = overview,
        language = originalLanguage,
        imageUrl = if(backdropPath.isNullOrEmpty()) formatPosterPath(poster_Path) else formatPosterPath(backdropPath),
        votes = voteCount,
        averageVotes = voteAverage,
        genreIds = genreIds
    )
}

fun List<Tv_show>.toTvShowsEntityList(): List<TvShowsEntity> {
    return map { it.toTvShowsEntity() }
}

fun Tv_show.toTvShowsEntity(): TvShowsEntity {
    return TvShowsEntity(
        id = id.toInt(),
        title = title,
        description = description,
        language = language,
        imageUrl = image_url,
        votes = votes.toInt(),
        averageVotes = vote_average,
        genreIds = genre_ids,
        showCategory = show_category
    )
}

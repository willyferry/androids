package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.NetworkBoundResource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class FakeMovieCatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogeDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(movieResponses: List<MoviesResponse>) {
                val movieList = ArrayList<MoviesEntity>()

                for (response in movieResponses) {
                    val movie = MoviesEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.genre,
                        response.rating,
                        response.release,
                        response.duration,
                        response.score,
                        response.type,
                        response.favorite,
                        response.image,
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMoviesOrShows(movieList)
            }
        }.asLiveData()
    }

    override fun getShows(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllShows()

            override fun saveCallResult(showResponses: List<MoviesResponse>) {
                val showList = ArrayList<MoviesEntity>()

                for (response in showResponses) {
                    val show = MoviesEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.genre,
                        response.rating,
                        response.release,
                        response.duration,
                        response.score,
                        response.type,
                        response.favorite,
                        response.image,
                    )
                    showList.add(show)
                }

                localDataSource.insertMoviesOrShows(showList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId : Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MoviesEntity> =
                localDataSource.getDetail(movieId)

            override fun shouldFetch(movieEntity: MoviesEntity?): Boolean =
                movieEntity == null

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MoviesResponse) {
                localDataSource.getDetail(movieId)
            }
        }.asLiveData()
    }

    override fun getDetailShow(showId: Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MoviesEntity> =
                localDataSource.getDetail(showId)

            override fun shouldFetch(movieEntity: MoviesEntity?): Boolean =
                movieEntity == null

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getDetailShow(showId)

            override fun saveCallResult(data: MoviesResponse) {
                localDataSource.getDetail(showId)
            }
        }.asLiveData()
    }

    override fun getAllMoviesFavorite(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()

        return LivePagedListBuilder(localDataSource.getAllMoviesFavorite(), config).build()
    }

    override fun getAllShowsFavorite(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()

        return LivePagedListBuilder(localDataSource.getAllShowsFavorite(), config).build()
    }

    override fun setFavorite(movieOrShow: MoviesEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavorite(movieOrShow, state)
        }

}
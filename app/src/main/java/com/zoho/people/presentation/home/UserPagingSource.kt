package com.zoho.people.presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.thedukerchip.domain.GetUserListUseCase
import dev.thedukerchip.domain.models.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

const val PAGING_START_INDEX = 1
const val PAGE_SIZE = 25

@Singleton
class UserPagingSource @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
) : PagingSource<Int, UserEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserEntity> {
        return try {
            val nextPage = params.key ?: PAGING_START_INDEX
            val users = getUserListUseCase(nextPage)

            LoadResult.Page(
                data = users,
                prevKey = if (nextPage == PAGING_START_INDEX) null else nextPage - 1,
                nextKey = if (users.isEmpty()) null else nextPage + (params.loadSize / PAGE_SIZE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
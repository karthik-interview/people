package com.zoho.people.data.remote

import com.zoho.people.data.common.ResponseItems
import com.zoho.people.models.data.RemoteUserEntity
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_PAGE = "page"
private const val QUERY_SEED = "seed"
private const val QUERY_PAGE_SIZE = "results"
private const val PATH_USERS = "api/"

object UserServiceDefaults {
    const val pageSize = 25
    const val seed = "people"
}

interface UserService {

    @GET(PATH_USERS)
    suspend fun getUsers(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_SEED) seed: String = UserServiceDefaults.seed,
        @Query(QUERY_PAGE_SIZE) pageSize: Int = UserServiceDefaults.pageSize,
    ): ResponseItems<RemoteUserEntity>
}

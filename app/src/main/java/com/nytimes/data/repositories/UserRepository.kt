package com.nytimes.data.repositories

import com.nytimes.data.network.MyApi
import com.nytimes.data.network.SafeApiRequest
import com.nytimes.data.response.GetMasters

class UserRepository(
    private val myApi: MyApi
) : SafeApiRequest() {

    suspend fun MasterMostpopular(

    ): GetMasters {
        return apiRequest { myApi.MasterMostpopular() }
    }




}
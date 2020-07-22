package com.nytimes.data.network

import com.nytimes.support.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let{
                try{ message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
                message.append("\n")
                Timber.e(message.toString())
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }

}
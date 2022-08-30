package com.giftpunding.osds.repository

import android.util.Log
import com.giftpunding.osds.callback.AddressSearchResultCallback
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.repository.remote.datasource.AddressDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository(private val addressDataSource: AddressDataSource) {
    fun getAddress(
        apiKey: String,
        query: String,
        page: Int,
        size: Int,
        callback: AddressSearchResultCallback,
    ) {
        val call = addressDataSource.getAddressService().getAddress(apiKey, query, page, size)
        call.enqueue(object : Callback<AddressSearchResultResponse> {
            override fun onResponse(
                call: Call<AddressSearchResultResponse>,
                response: Response<AddressSearchResultResponse>
            ) {
                if (response.isSuccessful) {
                    //success
                    Log.d("query", query)
                    callback.onSuccessAddressSearchResult(response.body() as AddressSearchResultResponse)
                } else {
                    callback.onFailureAddressSearchResult(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<AddressSearchResultResponse>, t: Throwable) {
                //err
                callback.onFailureAddressSearchResult(t.message.toString())
            }
        })
    }
}
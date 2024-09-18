package com.assignment.scrollsplitassignment.network

import com.assignment.scrollsplitassignment.viewmodel.model.ScrollItemModel
import com.assignment.scrollsplitassignment.viewmodel.requestResponse.ResultResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ravi Sankar on 07/09/24.
 */
interface APIInterface {

    @GET("b/5BEJ")
    suspend fun scrollList(): ArrayList<ScrollItemModel>

}
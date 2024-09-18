package com.assignment.scrollsplitassignment.viewmodel.repository

import com.assignment.scrollsplitassignment.network.APIInterface
import com.assignment.scrollsplitassignment.network.ApiFactory.client
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    private var apiInterface: APIInterface = client.create(APIInterface::class.java)
    suspend fun getDataList() = apiInterface.scrollList()

}

package com.assignment.scrollsplitassignment.viewmodel.requestResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultResponse<T>(@field:SerializedName("data") var result: T) {

    var isShowProgress = false
    var isShowError = false
    var isSuccess = false

    @SerializedName("status")
    @Expose
    var status = 0

    @SerializedName("user_status")
    @Expose
    var userStatus: String = ""

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    override fun toString(): String {
        return "ResultResponse(result=$result, isShowProgress=$isShowProgress, isShowError=$isShowError, isSuccess=$isSuccess, status=$status, message=$message, key=$key)"
    }

}
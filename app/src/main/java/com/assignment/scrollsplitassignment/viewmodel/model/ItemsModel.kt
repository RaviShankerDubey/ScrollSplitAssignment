package com.assignment.scrollsplitassignment.viewmodel.model

import com.google.gson.annotations.SerializedName

data class ItemsModel(
    @SerializedName("title") var title: String? = null,//
    @SerializedName("image") var image: String? = null//
) {
    override fun toString(): String {
        return "ItemsModel(title=$title, image=$image)"
    }
}

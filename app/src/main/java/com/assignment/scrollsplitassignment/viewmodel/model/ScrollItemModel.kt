package com.assignment.scrollsplitassignment.viewmodel.model

import com.google.gson.annotations.SerializedName

data class ScrollItemModel(
    @SerializedName("sectionType") var sectionType: String? = null,//
    @SerializedName("items") var items: ArrayList<ItemsModel> = arrayListOf()//
) {
    override fun toString(): String {
        return "ScrollItemModel(sectionType=$sectionType, items=$items)"
    }
}

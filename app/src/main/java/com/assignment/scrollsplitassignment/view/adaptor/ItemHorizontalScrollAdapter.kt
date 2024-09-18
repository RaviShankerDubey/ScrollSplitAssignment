package com.assignment.scrollsplitassignment.view.adaptor

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.scrollsplitassignment.R
import com.assignment.scrollsplitassignment.databinding.ItemHorizontalFreeScrollLayoutBinding
import com.assignment.scrollsplitassignment.viewmodel.model.ItemsModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ItemHorizontalScrollAdapter(private val mListener: OnClickListener, private val widthType: String) : RecyclerView.Adapter<ItemHorizontalScrollAdapter.ItemListViewHolder>() {

    var offerList: ArrayList<ItemsModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setItemList(list: ArrayList<ItemsModel>) {
        this.offerList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val binding = ItemHorizontalFreeScrollLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        /*binding.root.post {
            if (widthType == AppConstant.HORIZONTAL_FREE_SCROLL) {
                binding.root.layoutParams.width = 140
                binding.root.layoutParams.height = 140
            } else if (widthType == AppConstant.SPLIT_BANNER) {
                binding.root.layoutParams.width = parent.width / 2 - 20
                binding.root.layoutParams.height = 240
            }
            binding.root.requestLayout()
        }*/
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) = holder.bind(position, offerList?.get(position), mListener)

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return offerList?.size ?: 0
    }

    class ItemListViewHolder(val binding: ItemHorizontalFreeScrollLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos: Int, itemsModel: ItemsModel?, clickListener: OnClickListener) {
            Glide.with(binding.root.context).load(itemsModel?.image).apply(RequestOptions().placeholder(R.drawable.no_image)).into(binding.latestOfferImg)
            binding.brandTxt.text = itemsModel?.title

            binding.latestOfferImg.tag = pos
            binding.latestOfferImg.setOnClickListener(clickListener)

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemListViewHolder {
                val binding = DataBindingUtil.inflate<ItemHorizontalFreeScrollLayoutBinding>(LayoutInflater.from(parent.context), R.layout.item_horizontal_free_scroll_layout, parent, false)
                return ItemListViewHolder(binding)
            }
        }
    }

}
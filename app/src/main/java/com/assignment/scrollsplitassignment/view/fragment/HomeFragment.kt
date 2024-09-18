package com.assignment.scrollsplitassignment.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.scrollsplitassignment.databinding.FragmentHomeBinding
import com.assignment.scrollsplitassignment.utils.AppConstant
import com.assignment.scrollsplitassignment.view.activity.HomeActivity
import com.assignment.scrollsplitassignment.view.adaptor.DashboardSliderAdapter
import com.assignment.scrollsplitassignment.view.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.assignment.scrollsplitassignment.view.autoimageslider.SliderAnimations
import com.assignment.scrollsplitassignment.view.autoimageslider.SliderView
import com.assignment.scrollsplitassignment.viewmodel.HomeViewModel
import com.assignment.scrollsplitassignment.viewmodel.requestResponse.Status
import com.assignment.scrollsplitassignment.view.adaptor.ItemHorizontalScrollAdapter
import com.assignment.scrollsplitassignment.view.adaptor.ItemSplitBannerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private val binding get() = _binding!!
    private var viewModel: HomeViewModel? = null
    private var mHomeActivity: HomeActivity? = null
    private var _binding: FragmentHomeBinding? = null

    private lateinit var splitBannerAdapter: ItemSplitBannerAdapter
    private lateinit var horizontalFreeScrollAdapter: ItemHorizontalScrollAdapter
    private var sliderAdapter: DashboardSliderAdapter = DashboardSliderAdapter(mHomeActivity, this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mHomeActivity = activity as HomeActivity?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listItemAdapterSetup()
        getDataList()
    }

    private fun listItemAdapterSetup() {/*Banner Auto Slider Hide Right Now*/
        binding.imageSlider.setSliderAdapter(sliderAdapter)
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!

        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        binding.imageSlider.indicatorSelectedColor = Color.WHITE
        binding.imageSlider.indicatorUnselectedColor = Color.GRAY
        binding.imageSlider.scrollTimeInSec = 4
        binding.imageSlider.isAutoCycle = true
        binding.imageSlider.startAutoCycle()

        /* Horizontal Free Scroll */
        horizontalFreeScrollAdapter = ItemHorizontalScrollAdapter(this, AppConstant.HORIZONTAL_FREE_SCROLL)
        binding.horizontalFreeScrollRecyclerView.layoutManager = LinearLayoutManager(mHomeActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalFreeScrollRecyclerView.adapter = horizontalFreeScrollAdapter

        /* Split Banner */
        splitBannerAdapter = ItemSplitBannerAdapter(this)
        binding.splitBannerRecyclerView.layoutManager = LinearLayoutManager(mHomeActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.splitBannerRecyclerView.adapter = splitBannerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(mView: View?) {
        TODO("Not yet implemented")
    }

    private fun getDataList() {
        lifecycleScope.launch {
            viewModel?.getDataList()?.observe(viewLifecycleOwner, Observer { response ->
                response?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            val itemList = resource.data

                            val bannerList = itemList?.get(0)?.items
                            bannerList?.addAll(itemList[3].items)

                            sliderAdapter.renewItems(bannerList)
                            splitBannerAdapter.setItemList(itemList?.get(2)?.items!!)
                            horizontalFreeScrollAdapter.setItemList(itemList[1].items)

                            binding.imageSlider.visibility = View.VISIBLE
                            binding.sliderShimmerLayout.shimmerLayout.visibility = View.GONE

                            binding.splitBannerShimmerLayout.shimmerLayout.visibility = View.GONE
                            binding.horizontalFreeScrollShimmerLayout.shimmerLayout.visibility = View.GONE
                            Log.e("", "")
                        }

                        Status.ERROR -> {
                            Log.e("", "")
                        }

                        Status.LOADING -> {
                            Log.e("", "")
                        }
                    }
                }
            })
        }
    }
}
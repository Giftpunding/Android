package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.FragmentHomeBinding
import com.giftfunding.osds.ui.home.adapter.*
import com.giftfunding.osds.ui.model.MostSearchKeyword
import com.giftfunding.osds.util.RecyclerViewDeco
import com.giftfunding.osds.util.infinityBanner
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_home
    private val labelList = ArrayList<String>()

    private var category = arrayOf<String>()
    private var bannerSize: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init() {
        initSearchInputPopUp()
        initBanner()
        initBannerCountTextView()
        initMostSearchGift()
        initMostSelectGift()
        initJoinForceGift()
        initGiftCategory()
        shuffleCategory()
        initBasketGift()
        initMostSearchKeyword()
    }

    private fun initSearchInputPopUp() {
        binding.editSearchGift.showAlignTop(makeBalloon())
    }

    //말풍선 생성
    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(getString(R.string.content_home_search_popup))
            .setTextColorResource(R.color.solitude_2)
            .setTextTypeface(ResourcesCompat.getFont(requireContext(), R.font.pretendard_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setIconDrawableResource(R.drawable.ic_popup_gift)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(8)
            .setPaddingLeft(13)
            .setPaddingRight(13)
            .setPaddingBottom(8)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.midnight_express)

        return popUpMessage.build()
    }

    // 배너 초기화
    private fun initBanner() {
        val list = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_arrow_back,
            R.drawable.ic_arrow_top_scroll,
        )
        bannerSize = list.size

        val bannerAdapter = BannerAdapter(requireActivity())
        binding.vpHomeBanner.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        bannerAdapter.setBannerItems(list)
    }

    // 배너 페이지 초기화
    private fun initBannerCountTextView() {
        val bannerCountText = "$DEFAULT_PAGE / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    // 가장 많이 찾는 선물 초기화
    private fun initMostSearchGift() {
        val list = listOf(
            R.drawable.ic_gifticon,
            R.drawable.ic_food,
            R.drawable.ic_beauty,
            R.drawable.ic_fashion,
            R.drawable.ic_living,
            R.drawable.ic_luxury,
            R.drawable.ic_digital,
            R.drawable.ic_all
        )

        val mostSearchGiftAdapter = MostSearchGiftRecyclerViewAdapter()
        binding.contentSearchGift.rvMainCategory.apply {
            adapter = mostSearchGiftAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 14f, 0f, 15f))
        }
        mostSearchGiftAdapter.setItems(list)
    }

    // 가장 많이 선택한 선물
    private fun initMostSelectGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..8) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 40000,
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val mostSelectGiftAdapter = MostSelectGiftRecyclerViewAdapter()
        binding.contentMostSelectGift.rvMostSelectGiftList.apply {
            adapter = mostSelectGiftAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 12f, 0f, 0f))
        }

        mostSelectGiftAdapter.setItems(list)
        mostSelectGiftAdapter.setFullSize(false)
    }

    //힘을 모으면 살 수 있어 (명품)
    private fun initJoinForceGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..20) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 400000 + (idx * 100000),
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val luxuryGiftAdapter = LuxuryGiftRecyclerViewAdapter()
        binding.contentJoinForceGift.rvLuxuryList.apply {
            adapter = luxuryGiftAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 0f, 0f, 12f))
        }

        luxuryGiftAdapter.setItems(list)
    }

    //선물로 담을만한것이 뭐가 있더라에 표시할 카테고리 초기화
    private fun initGiftCategory() {
        category = resources.getStringArray(R.array.gift_category)
    }

    //선물로 담을만한것이 뭐가 있더라 카테고리 랜덤으로 섞기
    private fun shuffleCategory() {
        category.shuffle()
        initRecommendGift()
    }

    //선물로 담을만한것이 뭐가 있더라 카테고리 초기화
    private fun initRecommendGift() {
        //홈 프래그먼트 실행시 가장 앞에 카테고리는 선택, 해당 카테고리를 넣어줌
        changeBasketGiftButtonName(category[0])

        binding.contentBasketGift.rBtnFirstMoreGift.text = category[0]
        binding.contentBasketGift.rBtnSecondMoreGift.text = category[1]
        binding.contentBasketGift.rBtnThirdMoreGift.text = category[2]
        binding.contentBasketGift.rBtnForthMoreGift.text = category[3]
    }

    //선물로 담을만한것이 뭐가 있더라
    private fun initBasketGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..20) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 400000 + (idx * 100000),
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val basketGiftAdapter = BasketGiftRecyclerViewAdapter()
        binding.contentBasketGift.rvMoreGiftList.apply {
            adapter = basketGiftAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 28f, 0f, 9.5f))
        }
        basketGiftAdapter.setItems(list)
    }


    private fun initMostSearchKeyword() {
        val list = mutableListOf<MostSearchKeyword>()
        for (idx in 1..10) {
            list.add(
                MostSearchKeyword(
                    keyword = "키워드$idx",
                    rank = idx
                )
            )
        }

        val mostKeywordAdapter = MostKeywordGiftRecyclerViewAdapter()
        binding.contentMostKeywordGift.rvMostSearchedGiftList.apply {
            adapter = mostKeywordAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        mostKeywordAdapter.setItems(list)
    }


    override fun initEvent() {
        binding.vpHomeBanner.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateBannerPosition(position.infinityBanner(bannerSize))
            }
        })

        binding.contentBasketGift.btnRefresh.setOnClickListener {
            shuffleCategory()
        }

        binding.contentBasketGift.rBtnFirstMoreGift.setOnClickListener {
            changeBasketGiftButtonName(binding.contentBasketGift.rBtnFirstMoreGift.text.toString())
        }

        binding.contentBasketGift.rBtnSecondMoreGift.setOnClickListener {
            changeBasketGiftButtonName(binding.contentBasketGift.rBtnSecondMoreGift.text.toString())
        }

        binding.contentBasketGift.rBtnThirdMoreGift.setOnClickListener {
            changeBasketGiftButtonName(binding.contentBasketGift.rBtnThirdMoreGift.text.toString())
        }

        binding.contentBasketGift.rBtnForthMoreGift.setOnClickListener {
            changeBasketGiftButtonName(binding.contentBasketGift.rBtnForthMoreGift.text.toString())
        }

        binding.contentMostSelectGift.tvRankingMoreInfo.setOnClickListener {
            val selectedGiftType = binding.contentMostSelectGift.rgAllCategory.checkedRadioButtonId
            navigate(HomeFragmentDirections.actionHomeFragmentToGiftRankingFragment(selectedGiftType))
        }
    }

    private fun updateBannerPosition(position: Int) {
        val bannerCountText = "${position + 1} / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    private fun changeBasketGiftButtonName(categoryName: String) {
        val result = "$categoryName 더보기"
        binding.contentBasketGift.tvMoreGiftInfo.text = result
    }

    override fun initObserverEvent() {

    }

    companion object {
        private const val TAG = "HOME_FRAGMENT"
        private const val DEFAULT_PAGE = 1
    }
}
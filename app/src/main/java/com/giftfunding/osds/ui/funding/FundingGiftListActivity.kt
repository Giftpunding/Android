//package com.giftfunding.osds.ui.funding
//
//import android.os.Bundle
//import android.view.View
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.giftfunding.osds.R
//import com.giftfunding.osds.base.BaseActivity
//import com.giftfunding.osds.data.repository.remote.datasource.dto.funding.FundingResponse
//import com.giftfunding.osds.databinding.ActivityFundingGiftListBinding
//import com.giftfunding.osds.ui.funding.adapter.FundingListAdapter
//
//class FundingGiftListActivity : BaseActivity<ActivityFundingGiftListBinding>(ActivityFundingGiftListBinding::inflate), View.OnClickListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//        initEvent()
//    }
//
//    override fun init() {
//        /* 임시 더미데이터, 추후에 서버에서 받아올 예정 */
//        val fList = mutableListOf<FundingResponse>()
//        for (idx in 0 until 10) {
//            fList.add(
//                FundingResponse(
//                    brand = "브랜드${idx}",
//                    name = "상품이름",
//                    img = "test",
//                    nowFunding = 10000,
//                    possibleFunding = 30000,
//                    totalPrice = 40000
//                )
//            )
//        }
//
//        binding.apply {
//            val fundingAdapter = FundingListAdapter(this@FundingGiftListActivity)
//            rvGiftList.layoutManager =
//                LinearLayoutManager(this@FundingGiftListActivity, LinearLayoutManager.VERTICAL, false)
//            rvGiftList.adapter = fundingAdapter
//            fundingAdapter.addItemList(fList)
//
//
//            itemGiftListCategoryAll.apply {
//                setCategoryName(context.getString(R.string.category_all))
//                setFirstSelectItem()
//                setOnClickListener {
//                    clickCategory(
//                        itemGiftListCategoryOneToTwo,
//                        itemGiftListCategoryFiveToNine,
//                        itemGiftListCategoryThreeToFour,
//                        itemGiftListCategoryOverTen,
//                        itemGiftListCategoryAll
//                    )
//                }
//            }
//
//            itemGiftListCategoryOneToTwo.apply {
//                setCategoryName(context.getString(R.string.category_one_to_two_funding))
//                setOnClickListener {
//                    clickCategory(
//                        itemGiftListCategoryAll,
//                        itemGiftListCategoryFiveToNine,
//                        itemGiftListCategoryThreeToFour,
//                        itemGiftListCategoryOverTen,
//                        itemGiftListCategoryOneToTwo
//                    )
//                }
//            }
//
//            itemGiftListCategoryThreeToFour.apply {
//                setCategoryName(context.getString(R.string.category_three_to_four_funding))
//                setOnClickListener {
//                    clickCategory(
//                        itemGiftListCategoryOneToTwo,
//                        itemGiftListCategoryFiveToNine,
//                        itemGiftListCategoryAll,
//                        itemGiftListCategoryOverTen,
//                        itemGiftListCategoryThreeToFour
//                    )
//                }
//            }
//
//            itemGiftListCategoryFiveToNine.apply {
//                setCategoryName(context.getString(R.string.category_five_to_nine_funding))
//                setOnClickListener {
//                    clickCategory(
//                        itemGiftListCategoryOneToTwo,
//                        itemGiftListCategoryAll,
//                        itemGiftListCategoryThreeToFour,
//                        itemGiftListCategoryOverTen,
//                        itemGiftListCategoryFiveToNine
//                    )
//                }
//            }
//
//            itemGiftListCategoryOverTen.apply {
//                setCategoryName(context.getString(R.string.category_over_ten_funding))
//                setOnClickListener {
//                    clickCategory(
//                        itemGiftListCategoryOneToTwo,
//                        itemGiftListCategoryFiveToNine,
//                        itemGiftListCategoryThreeToFour,
//                        itemGiftListCategoryAll,
//                        itemGiftListCategoryOverTen
//                    )
//                }
//            }
//        }
//    }
//
//    override fun initEvent() {
//
//    }
//
//    override fun onClick(view: View?) {
//
//    }
//}
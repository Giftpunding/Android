package com.giftfunding.osds.ui.merchandise

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.ColorInfo
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.Merchandise
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.SizeInfo
import com.giftfunding.osds.databinding.FragmentMerchandiseOrderBinding
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseColorAdapter
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseReceiptAdapter
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseSizeAdapter
import com.giftfunding.osds.util.RecyclerViewDeco
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderMerchandiseBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMerchandiseOrderBinding
    private lateinit var colorInfo: ColorInfo
    private lateinit var sizeInfo: SizeInfo
    private val receiptAdapter = MerchandiseReceiptAdapter()
    private val test = ArrayList<Merchandise>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMerchandiseOrderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeOrderMerchandiseColor.ivOpenColor.setOnClickListener {
            binding.rvMerchandiseColor.visibility = View.VISIBLE
            binding.includeOrderMerchandiseColor.tvMerchandiseColorTitle.setTextColor(Color.BLACK)
            binding.includeOrderMerchandiseReceipt.root.visibility = View.GONE
        }

        val productColorInfo = ArrayList<ColorInfo>()
        // dummy
        val green =
            ColorInfo(
                "#27AE60",
                "DeepGreen"
            )
        val lightBlue =
            ColorInfo(
                "#329DFF",
                "LightBlue"
            )
        val red =
            ColorInfo(
                "#FF0000",
                "red"
            )
        val yellow =
            ColorInfo(
                "#FFFF00",
                "yellow"
            )
        val black =
            ColorInfo(
                "#000000",
                "black"
            )

        productColorInfo.add(green)
        productColorInfo.add(lightBlue)
        productColorInfo.add(red)
        productColorInfo.add(yellow)
        productColorInfo.add(black)

        val colorAdapter = MerchandiseColorAdapter { colorInfo ->
            updateUIAfterSelectColor(colorInfo)
        }

        binding.rvMerchandiseColor.apply {
            adapter = colorAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        colorAdapter.addItems(productColorInfo)


        val productSizeInfo = ArrayList<SizeInfo>()
//        val large = SizeInfo("L", "+10,000")
        val large =
            SizeInfo(
                "L",
                10000
            )
        val medium =
            SizeInfo(
                "M",
                5000
            )
        val small =
            SizeInfo(
                "S",
                3000
            )

        productSizeInfo.add(large)
        productSizeInfo.add(medium)
        productSizeInfo.add(small)

        val sizeAdapter = MerchandiseSizeAdapter { sizeInfo ->
            updateUIAfterSelectSize(sizeInfo)
        }

        binding.rvMerchandiseSize.apply {
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        sizeAdapter.addItems(productSizeInfo)


        binding.includeOrderMerchandiseReceipt.rvMerchandiseReceipt.apply {
            adapter = receiptAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(space))
        }
    }

    private fun updateUIAfterSelectColor(colorInfo: ColorInfo) {
        this.colorInfo = colorInfo

        binding.includeOrderMerchandiseColor.vMerchandiseColor.apply {
            setBackgroundColor(Color.parseColor(colorInfo.color))
            visibility = View.VISIBLE
        }

        binding.includeOrderMerchandiseColor.tvMerchandiseColorTitle.text = colorInfo.colorName
        binding.rvMerchandiseColor.visibility = View.GONE

        binding.includeOrderMerchandiseSize.root.visibility = View.VISIBLE
        binding.rvMerchandiseSize.visibility = View.VISIBLE
    }

    private fun updateUIAfterSelectSize(sizeInfo: SizeInfo) {
        this.sizeInfo = sizeInfo

        binding.includeOrderMerchandiseColor.tvMerchandiseColorTitle.text = "Colors"
        binding.includeOrderMerchandiseColor.tvMerchandiseColorTitle.setTextColor(R.color.echo_blue_2)
        binding.includeOrderMerchandiseColor.vMerchandiseColor.visibility = View.GONE

        binding.includeOrderMerchandiseSize.root.visibility = View.GONE
        binding.rvMerchandiseSize.visibility = View.GONE

        binding.includeOrderMerchandiseReceipt.root.visibility = View.VISIBLE

        val orderMerchandises =
            Merchandise(
                colorInfo = colorInfo,
                sizeInfo = sizeInfo
            )

        test.add(orderMerchandises)
        receiptAdapter.addItems(test)
        binding.includeOrderMerchandiseReceipt.tvOrderCount.text = test.size.toString()

        // 가격 -> 63000은 추후 데이터 받아오면
        val price: Int = 63000 * (test.size)
        var charge = 0
        for(i in 0 until test.size){
            charge += (test[i].sizeInfo.surCharge)
        }

        binding.btnPutProduct.setBackgroundColor(Color.parseColor("#272D3F"))
        binding.includeOrderMerchandiseReceipt.tvTvProductTotalPrice.text = (price + charge).toString()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        const val space : Float = 10F
    }
}
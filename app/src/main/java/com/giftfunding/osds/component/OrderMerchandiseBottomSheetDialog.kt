package com.giftfunding.osds.component

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.data.response.product.ColorInfo
import com.giftfunding.osds.data.response.product.SizeInfo
import com.giftfunding.osds.databinding.FragmentMerchandiseOrderBinding
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseColorAdapter
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseSizeAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderMerchandiseBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMerchandiseOrderBinding

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
        }

        val productColorInfo = ArrayList<ColorInfo>()
        // dummy
        val green = ColorInfo("#27AE60", "DeepGreen")
        val lightBlue = ColorInfo("#329DFF", "LightBlue")
        val red = ColorInfo("#FF0000", "red")
        val yellow = ColorInfo("#FFFF00", "yellow")
        val black = ColorInfo("#000000", "black")

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
        val large = SizeInfo("L", "+10,000")
        val medium = SizeInfo("M", "+5,000")
        val small = SizeInfo("S", "+3,000")

        productSizeInfo.add(large)
        productSizeInfo.add(medium)
        productSizeInfo.add(small)

        val sizeAdapter = MerchandiseSizeAdapter()
        binding.rvMerchandiseSize.apply {
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        sizeAdapter.addItems(productSizeInfo)
    }

    private fun updateUIAfterSelectColor(colorInfo: ColorInfo) {
        binding.includeOrderMerchandiseColor.vMerchandiseColor.apply {
            setBackgroundColor(Color.parseColor(colorInfo.color))
            visibility = View.VISIBLE
        }
        binding.includeOrderMerchandiseColor.tvMerchandiseColorTitle.text = colorInfo.colorName
        binding.rvMerchandiseColor.visibility = View.GONE

        binding.includeOrderMerchandiseSize.root.visibility = View.VISIBLE
        binding.rvMerchandiseSize.visibility = View.VISIBLE
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
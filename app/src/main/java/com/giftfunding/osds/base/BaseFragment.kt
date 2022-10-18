package com.giftfunding.osds.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>() : Fragment() {

    private var _binding : T? = null
    val binding : T get() = _binding!!

    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getFragmentBinding(inflater, container)
        navController = findNavController()
        return binding.root
    }

    //viewbinding을 위한 binding정보 가져오기
    abstract fun getFragmentBinding(inflater: LayoutInflater, container : ViewGroup?) : T

    //navigate로 프래그먼트 변경
    fun navigate(direction : NavDirections){
        navController.navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
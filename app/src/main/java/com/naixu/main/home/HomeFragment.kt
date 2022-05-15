package com.naixu.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naixu.R
import com.naixu.main.MainActivity
import com.naixu.ui.BaseFragment

class HomeFragment : BaseFragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.name ?: "HomeFragment::class.java.name"
    }
    private var mainActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
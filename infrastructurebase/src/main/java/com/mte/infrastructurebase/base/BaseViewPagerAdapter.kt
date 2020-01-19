package com.mte.infrastructurebase.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

open class BaseViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentList = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment = mFragmentList[position]


    override fun getCount(): Int = mFragmentList.size

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

}
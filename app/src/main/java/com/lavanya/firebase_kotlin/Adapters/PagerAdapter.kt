package com.lavanya.firebase_kotlin.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.text.FieldPosition

class PagerAdapter(fm: FragmentManager, fragmentsList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    private var fragments = arrayListOf<Fragment>()

    init {
        this.fragments = fragmentsList
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Chats"
            1 -> return "Calls"
            else -> return ""
        }
    }

}


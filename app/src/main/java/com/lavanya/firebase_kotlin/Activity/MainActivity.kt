package com.lavanya.firebase_kotlin.Activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.lavanya.firebase_kotlin.Adapters.PagerAdapter
import com.lavanya.firebase_kotlin.Fragments.Fragment_Calls
import com.lavanya.firebase_kotlin.Fragments.Fragment_Chat
import com.lavanya.firebase_kotlin.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentsList = arrayListOf<Fragment>()
        fragmentsList.add(Fragment_Chat())
        fragmentsList.add(Fragment_Calls())

        val pagerAdapter = PagerAdapter(supportFragmentManager, fragmentsList)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}

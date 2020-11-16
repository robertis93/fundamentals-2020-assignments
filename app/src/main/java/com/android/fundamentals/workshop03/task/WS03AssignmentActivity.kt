package com.android.fundamentals.workshop03.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop03.WS03RootFragment

class WS03AssignmentActivity : AppCompatActivity(), WS03RootFragment.TransactionsFragmentClicks {

    private val rootFragment =
        WS03RootFragment().apply { setClickListener(this@WS03AssignmentActivity) }
    private var countId: Int = 0
    private var addBackStack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, rootFragment)
            commit()
        }
    }


    override fun addToBackStack(value: Boolean) {
        //TODO(W3:5) add addToBackStack check
    }


    override fun addRedFragment() {
        countId++
        //TODO(W3:1) Add red fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
        //TODO(W3:5) add addToBackStack check
    }


    override fun addBlueFragment() {
        countId++
        //TODO(W3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
        //TODO(W3:5) add addToBackStack check
    }


    override fun removeLast() {
        if (supportFragmentManager.fragments.size > 1) {
            //TODO(W3:3) Remove fragment
            //TODO(W3:5) add addToBackStack check
        }
    }


    override fun replaceFragment() {
        countId++
        //TODO(W3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
        //TODO(W3:5) add addToBackStack check
    }


}
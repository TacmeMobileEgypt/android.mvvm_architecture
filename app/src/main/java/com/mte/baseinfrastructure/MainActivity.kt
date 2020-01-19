package com.mte.baseinfrastructure

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.base.BaseActivity

class MainActivity : BaseActivity<ViewDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

    }
}

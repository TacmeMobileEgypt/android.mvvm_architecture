package com.mte.baseinfrastructure

import android.os.Bundle
import androidx.databinding.ViewDataBinding
 import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

    }
}

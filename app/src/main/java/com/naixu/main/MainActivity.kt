package com.naixu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naixu.R
import com.naixu.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
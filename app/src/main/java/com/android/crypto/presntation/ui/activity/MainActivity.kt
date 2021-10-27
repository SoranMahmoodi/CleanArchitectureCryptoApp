package com.android.crypto.presntation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.crypto.R
import com.android.crypto.presntation.ui.fragment.coins.CoinsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_main, CoinsFragment())
            .commit()
    }
}
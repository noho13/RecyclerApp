package com.daimler.tss.recyclerapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.daimler.tss.recyclerapp.ui.LibraryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragment = LibraryFragment.createInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .commit()
        }
    }
}

package de.eschoenawa.leakyfragmentmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragmentA()
    }

    fun navigateToFragmentA() {
        val fragmentA = FragmentA()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragmentA)
        }.commit()
    }

    fun navigateToFragmentB() {
        val fragmentB = FragmentB()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragmentB)
        }.commit()
    }
}
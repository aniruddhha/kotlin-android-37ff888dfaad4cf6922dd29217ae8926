package com.ani.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainerView, StartFragment())
            }
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainerView,MidFragment())
            }
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainerView, EndFragment())
            }
        }
    }
}
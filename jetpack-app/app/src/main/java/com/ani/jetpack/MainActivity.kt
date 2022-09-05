package com.ani.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ani.jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private fun viewBindingWay() {
        //private lateinit var binding: ActivityMainBinding
        // import com.ani.jetpack.databinding.ActivityMainBinding

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.button.setOnClickListener {
//            supportFragmentManager.commit {
//                replace(R.id.fragmentContainerView, StartFragment())
//            }
//        }
//        binding.button2.setOnClickListener {
//            supportFragmentManager.commit {
//                replace(R.id.fragmentContainerView,MidFragment())
//            }
//        }
//        binding.button3.setOnClickListener {
//            supportFragmentManager.commit {
//                replace(R.id.fragmentContainerView, EndFragment())
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.clk.observe(this) {
            Log.i("@ani", "In Activity $it")
        }
    }
}
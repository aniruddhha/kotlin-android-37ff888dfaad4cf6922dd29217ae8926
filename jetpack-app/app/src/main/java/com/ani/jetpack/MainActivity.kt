package com.ani.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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

        viewModel.clk.observe(this) { frag ->
            frag?.let {
                when(it) {
                    "start" ->  {

                    }
                    "mid" -> {
                        val action = StartFragmentDirections.actionStartFragmentToMidFragment()
                        appNav().navigate(action)
                    }
                    "end" -> {
                        val action = MidFragmentDirections.actionMidFragmentToEndFragment()
                        appNav().navigate(action)
                    }
                }
            }
        }
    }

//    fun changeFragment(frag: Fragment) {
//        supportFragmentManager.commit {  replace(R.id.fragmentContainerView, frag)  }
//    }

    fun appNav() : NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navHostFragment.navController
    }
}
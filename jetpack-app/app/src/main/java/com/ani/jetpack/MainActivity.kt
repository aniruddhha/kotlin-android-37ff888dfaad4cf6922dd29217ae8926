package com.ani.jetpack

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ani.jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
        if(isGranted) {
            Log.i("@ani", "Permission Granted")
        }
    }

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

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("@ani", "Permission Granted")
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {

                Log.i("@ani", "Showing Dialog")
                val dialog = AlertDialog.Builder(this)
                    .setPositiveButton("Yes") { di, id -> requestPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )  }
                    .setNegativeButton("No") { di, id -> di.dismiss() }
                    .setMessage("We Need Permission")
                    .create()

                dialog.show()
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }

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
package com.ani.industrystandardproject.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ani.industrystandardproject.R
import com.ani.industrystandardproject.databinding.ActivityMainBinding
import com.ani.industrystandardproject.organization.api.OrganizationApi
import com.ani.industrystandardproject.organization.domain.Organization
import com.ani.industrystandardproject.organization.dto.CreateOrganizationResponse
import com.ani.industrystandardproject.organization.fragment.CreateOrganizationFragment
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.info = MainInfo("abc", "2022-01-01, 01:10 PM")
        binding.lifecycleOwner = this

    }

    fun appNav() : NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        return navHostFragment.navController
    }

}
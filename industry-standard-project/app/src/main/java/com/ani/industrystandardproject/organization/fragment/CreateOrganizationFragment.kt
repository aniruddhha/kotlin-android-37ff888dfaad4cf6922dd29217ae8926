package com.ani.industrystandardproject.organization.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ani.industrystandardproject.R
import com.ani.industrystandardproject.databinding.FragmentCreateOrganizationBinding
import com.ani.industrystandardproject.main.MainActivity
import com.ani.industrystandardproject.organization.viewmodel.CreateOrganizationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateOrganizationFragment : Fragment() {

    val viewModel: CreateOrganizationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCreateOrganizationBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.btn.observe(viewLifecycleOwner) { uuid ->
            uuid?.let {
                val action = CreateOrganizationFragmentDirections.actionCreateOrganizationFragmentToListOrganizationFragment();
                (activity as MainActivity).appNav().navigate(action)
            }
        }

        return binding.root
    }

}
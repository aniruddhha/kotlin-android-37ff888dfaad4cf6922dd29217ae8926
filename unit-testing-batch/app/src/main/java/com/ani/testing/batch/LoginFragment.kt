package com.ani.testing.batch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ani.testing.batch.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = FragmentLoginBinding.inflate(inflater, container, false)
        root.vm = viewModel
        root.lifecycleOwner = viewLifecycleOwner

        return root.root
    }
}
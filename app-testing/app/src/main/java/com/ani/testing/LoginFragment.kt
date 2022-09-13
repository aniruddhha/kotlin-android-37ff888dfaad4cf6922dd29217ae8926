package com.ani.testing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ani.testing.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val root = FragmentLoginBinding.inflate(inflater, container, false)
        root.lifecycleOwner = viewLifecycleOwner
        root.vm = viewModel

        return root.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lgn.observe(viewLifecycleOwner) {
            it?.let{
                Log.i("@ani", "It")
            }
        }
    }
}
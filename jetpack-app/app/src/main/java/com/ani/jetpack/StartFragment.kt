package com.ani.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ani.jetpack.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val viewModel : StartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentStartBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.btn.observe(viewLifecycleOwner) { bt ->
            bt?.let {
                if(it.isEmpty().not()) {
                    val hostActivity = activity as MainActivity

                    val action = StartFragmentDirections.actionStartFragmentToMidFragment()
                    hostActivity.appNav().navigate(action)

//                    hostActivity.changeFragment(MidFragment())
                }
            }
        }
    }

    companion object {
        fun newInstance() = StartFragment()
    }
}
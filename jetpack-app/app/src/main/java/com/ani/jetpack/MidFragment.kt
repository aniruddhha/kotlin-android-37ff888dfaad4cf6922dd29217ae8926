package com.ani.jetpack

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class MidFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ll = LinearLayoutManager(activity)
        view.findViewById<RecyclerView>(R.id.rec).apply {
            layoutManager = ll
            addItemDecoration(
                DividerItemDecoration(requireContext(), ll.orientation)
            )
            adapter =  HiAdapter(
                requireContext(),
                listOf(
                    Msg(from = "abc", msg = "Hey hi", dt = LocalDate.of(2021, 1 ,1).toString()),
                    Msg(from = "par", msg = "I am good", dt = LocalDate.of(2022, 10 ,1).toString()),
                    Msg(from = "xyz", msg = "wow good", dt = LocalDate.of(2022, 11 ,11).toString())
                )
            )
        }
    }

    companion object {

        fun newInstance() = MidFragment()
    }
}
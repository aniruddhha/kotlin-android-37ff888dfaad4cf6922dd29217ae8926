package com.ani.industrystandardproject.organization.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ani.industrystandardproject.R
import com.ani.industrystandardproject.organization.api.OrganizationApi
import com.ani.industrystandardproject.organization.domain.Organization
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/*
  - NFRS
    - TLS communication
    - custom permissions
    - sqlite encryption
    - keystore storage
    - nw config
    - file provider
*/



@AndroidEntryPoint
class ListOrganizationFragment : Fragment() {

    @Inject
    lateinit var api : OrganizationApi

    private val lvDt  = MutableLiveData<Organization>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_organization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lvDt.observe( viewLifecycleOwner ) {
            Log.i("@ani", "Clicked Id ${it.id}")
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recOrgs)

        val llm = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = llm
        val deco = DividerItemDecoration(requireContext(), llm.orientation)
        recyclerView.addItemDecoration(deco)

        api.findAll(
            "",
            ""
        ).enqueue( object : Callback<List<Organization>> {
            override fun onResponse(
                call: Call<List<Organization>>,
                response: Response<List<Organization>>
            ) {
                response.body()?.let {
                    recyclerView.adapter =  OrgVwAdapter(requireContext(), it, lvDt)
                }
            }

            override fun onFailure(call: Call<List<Organization>>, t: Throwable) {
            }
        } )
    }
}
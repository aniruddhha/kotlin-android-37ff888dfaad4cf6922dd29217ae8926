package com.ani.industrystandardproject.organization.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ani.industrystandardproject.R
import com.ani.industrystandardproject.organization.domain.Organization

class OrgVwHld(
    val vw : View
) : RecyclerView.ViewHolder(vw) {

    fun setDisplayName(name  :String) {
        vw.findViewById<TextView>(R.id.txtDspNm).text = name
    }

    fun setDescription(name  :String) {
        vw.findViewById<TextView>(R.id.txtDesc).text = name
    }

    fun getDeleteButton() = vw.findViewById<ImageView>(R.id.imDel)
}

class OrgVwAdapter(
    private val ctx : Context,
    private val orgs : List<Organization>,
    private val lvDt : MutableLiveData<Organization>
) : RecyclerView.Adapter<OrgVwHld>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrgVwHld {
        val vw = LayoutInflater.from(ctx).inflate(R.layout.rec_org_itm, parent, false)
        return OrgVwHld(vw)
    }

    override fun onBindViewHolder(holder: OrgVwHld, position: Int) {
        holder.setDisplayName(orgs[position].displayName)
        holder.setDescription(orgs[position].desc)
        holder.getDeleteButton().setOnClickListener {
            lvDt.value = orgs[position]
        }
    }

    override fun getItemCount() = orgs.size
}
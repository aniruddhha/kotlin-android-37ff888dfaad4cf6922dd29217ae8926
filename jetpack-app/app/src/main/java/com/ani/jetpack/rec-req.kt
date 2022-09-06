package com.ani.jetpack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Msg(
    val from : String,
    val dt : String,
    val msg : String
)

class HiAdapter(
    private val ctx : Context,
    private val msgs : List<Msg>
) : RecyclerView.Adapter<HiViewHolder>() {

    private val inf = LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiViewHolder {
        val vw = inf.inflate(R.layout.rec_itm, parent, false)
        return HiViewHolder(vw)
    }

    override fun onBindViewHolder(holder: HiViewHolder, position: Int) {
        holder.setFrom(msgs[position].from)
        holder.setDt(msgs[position].dt)
        holder.setMsg(msgs[position].msg)
    }

    override fun getItemCount(): Int = msgs.size
}

class HiViewHolder(
    private val vw : View
) : RecyclerView.ViewHolder(vw) {

    fun setMsg(msg : String): TextView = vw.findViewById<TextView>(R.id.textView2).apply { text = msg }
    fun setFrom(msg : String): TextView = vw.findViewById<TextView>(R.id.textView).apply { text = msg }
    fun setDt(msg : String): TextView = vw.findViewById<TextView>(R.id.textView4).apply { text = msg }
}
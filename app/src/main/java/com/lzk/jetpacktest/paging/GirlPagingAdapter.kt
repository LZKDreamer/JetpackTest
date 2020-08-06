package com.lzk.jetpacktest.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lzk.jetpacktest.R
import com.lzk.jetpacktest.api.bean.GirlDetail

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/6 14:11
 * Description:
 */
class GirlPagingAdapter : PagingDataAdapter<GirlDetail,GirlViewHolder>(POST_COMPARATOR) {

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<GirlDetail>() {
            override fun areContentsTheSame(oldItem: GirlDetail, newItem: GirlDetail): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: GirlDetail, newItem: GirlDetail): Boolean =
                oldItem._id == newItem._id
        }
    }

    override fun onBindViewHolder(holder: GirlViewHolder, position: Int) {
        holder.descTv.text = getItem(position)?.desc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlViewHolder {
        return GirlViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_girl,parent,false))
    }
}

class GirlViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val descTv: TextView = view.findViewById(R.id.item_girl_desc_tv)
}
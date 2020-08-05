package com.lzk.jetpacktest.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lzk.jetpacktest.R
import com.lzk.jetpacktest.api.bean.GirlDetail
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.item_girl.view.*

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 15:23
 * Description:
 */
class GirlAdapter(val context: Context) : PagingDataAdapter<GirlDetail,GirlAdapter.ArticleViewHolder>(POST_COMPARATOR) {

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<GirlDetail>() {
            override fun areContentsTheSame(oldItem: GirlDetail, newItem: GirlDetail): Boolean =
                oldItem.images == newItem.images

            override fun areItemsTheSame(oldItem: GirlDetail, newItem: GirlDetail): Boolean =
                oldItem._id == newItem._id
        }
    }

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var nameTv: TextView = itemView.item_girl_desc_tv
        var girlIv: ImageView = itemView.item_girl_iv.apply {
            setOnClickListener(this@ArticleViewHolder)
        }
        override fun onClick(v: View) {
            mOnItemClickListener?.onItemClick(v,this@GirlAdapter.getItem(bindingAdapterPosition)!!,bindingAdapterPosition)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(view: View,data: GirlDetail,position: Int)
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener){
        mOnItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.nameTv.text = getItem(position)?.title
        Glide.with(context).load(getItem(position)?.url) .apply(RequestOptions.bitmapTransform(
            BlurTransformation(20, 3)
        ))
            .thumbnail(.3f).into(holder.girlIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_girl,parent,false))
    }
}
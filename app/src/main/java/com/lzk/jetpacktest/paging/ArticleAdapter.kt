package com.lzk.jetpacktest.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lzk.jetpacktest.R
import com.lzk.jetpacktest.api.bean.Article
import kotlinx.android.synthetic.main.item_wx_article.view.*

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 15:23
 * Description:
 */
class ArticleAdapter : PagingDataAdapter<Article,ArticleAdapter.ArticleViewHolder>(POST_COMPARATOR) {

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.chapterId == newItem.chapterId

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id
        }
    }

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var nameTv: TextView = itemView.item_author_name_tv
        override fun onClick(v: View) {
            
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.nameTv.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wx_article,parent,false))
    }
}
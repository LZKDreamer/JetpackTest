package com.lzk.jetpacktest.api.bean

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/5 16:25
 * Description:
 */
data class GirlResponse(
    val `data`: List<GirlDetail>,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
)

data class GirlDetail(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)
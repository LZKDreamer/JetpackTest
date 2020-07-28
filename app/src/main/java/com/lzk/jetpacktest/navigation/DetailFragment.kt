package com.lzk.jetpacktest.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("DetailFragment","onCreateView")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var argStr: String? = null
        arguments?.apply {
            argStr = getString("arg","参数为空")
        }
        view?.apply {
            detail_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment))
            if (argStr != null){
                detail_tv.text = "${detail_tv.text}:${argStr}"
            }
        }
    }
}
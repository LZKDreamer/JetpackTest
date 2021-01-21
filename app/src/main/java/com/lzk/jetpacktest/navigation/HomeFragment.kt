package com.lzk.jetpacktest.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lzk.jetpacktest.R
import com.lzk.libnavannotation.FragmentDestination
import kotlinx.android.synthetic.main.fragment_home.*

@FragmentDestination(pageUrl = "main/home",asStarter = true)
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("HomeFragment","onCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.apply {
            home_btn.setOnClickListener {
                val navController: NavController = Navigation.findNavController(it)
                val bundle: Bundle = Bundle()
                bundle.putString("arg","我是参数")
                navController.navigate(R.id.action_homeFragment_to_detailFragment,bundle)
            }
        }
    }
}
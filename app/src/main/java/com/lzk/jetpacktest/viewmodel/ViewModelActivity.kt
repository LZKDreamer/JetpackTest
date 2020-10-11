package com.lzk.jetpacktest.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lzk.jetpacktest.R

class ViewModelActivity : AppCompatActivity() {

    //创建ViewModel
    //ViewModelOne需要一个Application参数，但下面都没有传Application对象，因为defaultViewModelProviderFactory里面会通过调用getApplication()获取Application
   private val vmOne by ViewModelLazy<ViewModelOne>(ViewModelOne::class,{viewModelStore},{defaultViewModelProviderFactory})

    private val vmTwo by viewModels<ViewModelOne> { defaultViewModelProviderFactory }

    private val vmThree: ViewModelOne by lazy {
        ViewModelProvider(viewModelStore,defaultViewModelProviderFactory).get(ViewModelOne::class.java)
    }

    private val vmFourth: ViewModelTwo by lazy {
        ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelTwo::class.java)
    }

    private val vmFive: ViewModelTwo by viewModels { defaultViewModelProviderFactory }

    private val vmCustom: CustomViewModel by lazy {
        ViewModelProviders.of(this,MyViewModelFactory("我是ViewModel参数")).get(CustomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        vmOne.method()
        vmOne.printSomething()
        vmTwo.printSomething()
        vmThree.printSomething()
        vmFourth.printSomething("fourth")
        vmFive.printSomething("five")

        vmCustom.printParam()
    }

    //============打印结果=================
//    2020-10-11 11:02:35.907 6573-6573/com.lzk.jetpacktest D/ViewModelOne: ViewModelOne:JetpackTest
//    2020-10-11 11:02:35.907 6573-6573/com.lzk.jetpacktest D/ViewModelOne: ViewModelOne print something
//    2020-10-11 11:02:35.908 6573-6573/com.lzk.jetpacktest D/ViewModelOne: ViewModelOne print something
//    2020-10-11 11:02:35.908 6573-6573/com.lzk.jetpacktest D/ViewModelTwo: ViewModelTwo print something:fourth
//    2020-10-11 11:02:35.908 6573-6573/com.lzk.jetpacktest D/ViewModelTwo: ViewModelTwo print something:five
//    2020-10-11 11:40:17.565 9965-9965/com.lzk.jetpacktest D/CustomViewModel: param:我是ViewModel参数
}
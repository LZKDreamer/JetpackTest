package com.lzk.jetpacktest.coroutine

import android.util.Log
import com.lzk.jetpacktest.api.HttpRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Author: LiaoZhongKai.
 * Date: 2020/10/6
 * Function:
 *
 **/
fun main(){
    val rb1 = runBlocking {
        //launch() 异步，不阻塞线程，返回值Job
        val a = launch {
            println("***coroutine a***")
        }
        val b = launch {
            println("***coroutine b***")
        }

        //async() 异步，不阻塞线程 返回值Deferred
        val c = async {
            println("***coroutine c***")
            delay(1000)
            "我是c"//结果
        }
        val d = async {
            println("***coroutine d***")
//            delay(2000)
//            "我是d"
            //等挂起函数执行完后才会继续执行下面的代码
            val response = HttpRepository().requestGirlList(1)
            val size = response.data.size
            println("******response:$size******")
        }

        //await()获取async返回的值，当两个async执行完成后才会执行下面的这句代码
        println("result--> C:${c.await()},d:${d.await()}")
        //当await执行完成后才会往下执行
        println("await()后面的语句")

        delay(3300)

        val aJoin = launch {
            delay(1000)
            println("***coroutine aJoin***")
        }
        //将aJoin加入到当前的协程中（即runBlocking），并且只有当aJoin执行完后才会执行后面的语句
        aJoin.join()

        val bJoin = launch {
            delay(3000)
            println("***coroutine bJoin***")
        }
        bJoin.join()
        println("end--->Join")
    }


    //=======================执行结果========================
//    ***coroutine a***
//    ***coroutine b***
//    ***coroutine c***
//    ***coroutine d***
//    十月 11, 2020 9:59:21 上午 okhttp3.internal.platform.Platform log
//    信息: --> GET https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
//    ......
//    信息: {"data":[{"_id":"5e959250808d6d2fe6b56eda","author":"\u9e22\u5a9b","category":"Girl",
//    "likeCounts":4,"publishedAt":"2020-05-25 08:00:00","stars":1,"title":"\u7b2c96\u671f",
//    "type":"Girl","url":"http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2","views":8036},
//    .......
//    十月 11, 2020 9:59:22 上午 okhttp3.internal.platform.Platform log
//    信息: <-- END HTTP (5491-byte body)
//    ******response:10******
//    result--> C:我是c,d:kotlin.Unit
//    await()后面的语句
//    ***coroutine aJoin***
//    ***coroutine bJoin***
//    end--->Join

}
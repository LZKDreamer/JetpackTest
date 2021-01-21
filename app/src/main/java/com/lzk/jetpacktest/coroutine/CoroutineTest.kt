package com.lzk.jetpacktest.coroutine

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
    //runBlocking 阻塞线程,runBlocking后面的语句会等待runBlocking执行完之后才会执行
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
            delay(2000)
            "我是d"
        }

        //await()获取async返回的值，当两个async执行完成后才会执行下面的这句代码
        println("result--> C:${c.await()},d:${d.await()}")

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

    println("runBlocking后面的语句会等待runBlocking执行完之后才会执行")
}
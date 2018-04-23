package com.jimliuxyz.atylife

import android.app.Application
import android.os.Build

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        LifeLog.add("AppCreate", "red")
        LifeLog.add("RELEASE:${Build.VERSION.RELEASE}")
        LifeLog.add("SDK_INT:${Build.VERSION.SDK_INT}")
        LifeLog.end()
    }

    init {
        var cnt = 0
        Thread{
            while (true){
                Thread.sleep(3000)
                println(".${++cnt}")
            }
        }.start()
    }

    /**
     * there is no way to know an application killed, and we should't try to work it around!
     * everything you try to do may put it when onStop or implement a Service
     */

}

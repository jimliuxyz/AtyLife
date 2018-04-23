package com.jimliuxyz.atylife

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    init {
        LifeLog.add("ActivityCreate", "red")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LifeLog.add("create (${if(savedInstanceState!=null)"state" else "null"})")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        LifeLog.add("detach")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LifeLog.add("attach")
    }

    override fun onRestart() {
        super.onRestart()
        LifeLog.add("restart")
    }

    override fun onStop() {
        super.onStop()
        LifeLog.add("stop")
    }

    override fun onStart() {
        super.onStart()
        LifeLog.add("start")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        LifeLog.add("save")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        LifeLog.add("restore")
    }

    override fun onResume() {
        super.onResume()
        LifeLog.add("resume", hint = "${(Math.random() * 1000).toInt()}")
        LifeLog.end()

        tv?.setText(Html.fromHtml(LifeLog.log()))
    }

    override fun onPause() {
        super.onPause()
        LifeLog.add("pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LifeLog.add("destroy")
    }

    fun doFinish(view: View){
        finish()
    }
}

data class Item(var event: String, var color: String?, var hint: String?) {
    fun toHtml(): String {
        var hint = hint?.let { "($it)" } ?: ""
        var event = color?.let { "<font color=$color>$event</font>" } ?: event

        return event + hint
    }
}

object LifeLog {

    var fulllist = ArrayList<ArrayList<Item>>()
    var list = ArrayList<Item>()

    fun add(event: String, color: String? = null, hint: String? = null) {
        list.add(Item(event, color, hint))
        println(" >>>> ${event}")
    }

    fun end() {
        fulllist.add(list)
        list = ArrayList<Item>()
    }

    fun log(): String {
        var str = ""

        for (list in fulllist.reversed()) {
            var firstevent = true
            var started = false
            for (item in list) {
                if (!firstevent) str += " ⇨ "
                firstevent = false

                with(item) {
                    if (!started && (event == "create" || event == "restart" || event == "start" || event == "resume")) {
                        started = true
                        color = "blue"
                    }
                    str += toHtml()
                }
            }
            str += "<br>---<br><br>"
        }
        return str
    }
}

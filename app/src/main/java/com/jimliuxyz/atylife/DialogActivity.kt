package com.jimliuxyz.atylife

import android.app.Activity
import android.os.Bundle
import android.view.View

class DialogActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }

    fun doFinish(view: View){
        finish()
    }
}

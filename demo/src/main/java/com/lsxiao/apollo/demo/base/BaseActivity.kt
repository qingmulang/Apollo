package com.lsxiao.apollo.demo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lsxiao.apollo.core.Apollo.Companion.bind
import com.lsxiao.apollo.core.contract.ApolloBinder

abstract class BaseActivity<T> : AppCompatActivity() {
    private var mBinder: ApolloBinder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        mBinder = bind(this)
        afterCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinder!!.unbind()
    }

    protected abstract val layoutId: Int
    protected abstract fun afterCreate(savedInstanceState: Bundle?)
}
package com.lsxiao.apollo.demo

import android.app.Application
import com.lsxiao.apollo.core.Apollo.Companion.init
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

/**
 * author lsxiao
 * date 2016-08-08 13:33
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init(AndroidSchedulers.mainThread(), this, true)
    }
}
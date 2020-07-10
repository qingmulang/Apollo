package com.lsxiao.apollo.demo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import com.lsxiao.apollo.core.Apollo.Companion.emit
import com.lsxiao.apollo.demo.model.User

/**
 * write with Apollo
 * author:lsxiao
 * date:2017-05-15 15:18
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */
class TestService : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        emit("ipc", User("a User from TestService and pid is" + Process.myPid()))
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
package com.lsxiao.apollo.ipc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Process
import com.lsxiao.apollo.core.Apollo.Companion.getSerializer
import com.lsxiao.apollo.core.Apollo.Companion.transfer
import com.lsxiao.apollo.core.entity.Event

/**
 * write with Apollo
 * author:lsxiao
 * date:2017-05-15 14:28
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */
class ApolloProcessEventReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!intent.hasExtra(KEY_EVENT) || intent.getByteArrayExtra(KEY_EVENT) == null) {
            return
        }
        val event: Event
        event = try {
            getSerializer().deserialize(intent.getByteArrayExtra(KEY_EVENT), Event::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
        if (event.pid == Process.myPid()) {
            return
        }
        transfer(event)
    }

    companion object {
        var KEY_EVENT = "event"
    }
}
package com.lsxiao.apollo.demo

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.lsxiao.apollo.core.annotations.Receive
import com.lsxiao.apollo.demo.base.BaseActivity
import com.lsxiao.apollo.demo.fragment.ProducerFragment
import com.lsxiao.apollo.demo.fragment.SubscriberFragment
import com.lsxiao.apollo.demo.model.User

class TestActivity : BaseActivity<User?>() {
    private var mLastEventMessage: String? = null
    override val layoutId: Int = R.layout.activity_main

    override fun afterCreate(savedInstanceState: Bundle?) {
        val subscriberFragment: Fragment = SubscriberFragment.newInstance()
        val producerFragment: Fragment = ProducerFragment.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_subscriber, subscriberFragment, SubscriberFragment.TAG)
                .commit()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_producer, producerFragment, SubscriberFragment.TAG)
                .commit()
        (findViewById<View>(R.id.btn_start_service) as TextView).text = String.format("Start TestService(cur pid=%s)", Process.myPid())
        findViewById<View>(R.id.btn_start_service).setOnClickListener {
            val intent = Intent(this@TestActivity, TestService::class.java)
            startService(intent)
        }
    }

    @Receive("ipc")
    fun onIPCEvent(user: User) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show()
    }

    @Receive("testRegisterAndEmit")
    fun testRegisterAndEmit(message: String?) {
        mLastEventMessage = message
    }
}
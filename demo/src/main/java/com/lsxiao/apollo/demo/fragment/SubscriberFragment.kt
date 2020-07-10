package com.lsxiao.apollo.demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lsxiao.apollo.core.annotations.Receive
import com.lsxiao.apollo.demo.R
import com.lsxiao.apollo.demo.base.BaseFragment
import com.lsxiao.apollo.demo.constant.Event
import com.lsxiao.apollo.demo.model.User

/**
 * author lsxiao
 * date 2016-08-23 19:14
 */
open class SubscriberFragment : BaseFragment() {
    private var mTvReceiveEvent: TextView? = null
    private var mTvReceiveStickyEvent: TextView? = null
    override val layoutId: Int
        protected get() = R.layout.fragment_subscriber

    override fun afterCreate(savedInstanceState: Bundle?) {
        mTvReceiveEvent = mRootView!!.findViewById<View>(R.id.tv_received_event) as TextView
        mTvReceiveStickyEvent = mRootView!!.findViewById<View>(R.id.tv_received_sticky_event) as TextView
    }

    @Receive(Event.DOBLUE_NUMBER)
    fun onEvent(value: Double) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    @Receive(Event.FLOAT_NUMBER)
    fun onEvent(value: Float) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    @Receive(Event.INT_NUMBER)
    fun onEvent(value: Int) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    @Receive(Event.OBJECT)
    fun onEvent() {
    }

    @Receive(Event.OBJECT)
    fun onEvent(value: User) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    @Receive(Event.BOOL)
    fun onEvent(value: Boolean) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    @Receive(Event.STR)
    fun onEvent(value: String) {
        mTvReceiveEvent!!.text = mTvReceiveEvent!!.text.toString() + value + ","
    }

    companion object {
        const val TAG = "SubscriberFragment"
        fun newInstance(): SubscriberFragment {
            val args = Bundle()
            val fragment = SubscriberFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
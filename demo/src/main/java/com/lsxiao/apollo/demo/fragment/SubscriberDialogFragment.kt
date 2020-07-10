package com.lsxiao.apollo.demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lsxiao.apollo.core.annotations.Backpressure
import com.lsxiao.apollo.core.annotations.Receive
import com.lsxiao.apollo.core.annotations.Sticky
import com.lsxiao.apollo.demo.R
import com.lsxiao.apollo.demo.base.BaseDialogFragment
import io.reactivex.rxjava3.core.BackpressureStrategy

/**
 * author lsxiao
 * date 2016-08-23 21:01
 */
class SubscriberDialogFragment : BaseDialogFragment() {
    private var mTvSentStickyEvent: TextView? = null
    override val layoutId: Int
        protected get() = R.layout.dialog_subscriber

    @Sticky
    @Receive("sticky")
    fun onReceiveStickyEvent(event: String) {
        mTvSentStickyEvent!!.text = mTvSentStickyEvent!!.text.toString() + event + ","
    }

    override fun afterCreate(savedInstanceState: Bundle?) {
        mTvSentStickyEvent = mRootView!!.findViewById<View>(R.id.tv_sent_sticky_event) as TextView
    }

    @Backpressure(BackpressureStrategy.DROP)
    @Receive("event")
    fun onReceiveEvent(event: String) {
        mTvSentStickyEvent!!.text = mTvSentStickyEvent!!.text.toString() + event + ","
    }

    companion object {
        const val TAG = "SubscriberDialogFragment"
        fun newInstance(): SubscriberDialogFragment {
            val args = Bundle()
            val fragment = SubscriberDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
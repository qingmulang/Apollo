package com.lsxiao.apollo.demo.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lsxiao.apollo.core.Apollo.Companion.emit
import com.lsxiao.apollo.demo.R
import com.lsxiao.apollo.demo.base.BaseFragment
import com.lsxiao.apollo.demo.constant.Event
import com.lsxiao.apollo.demo.model.User

/**
 * author lsxiao
 * date 2016-08-23 19:29
 */
class ProducerFragment : BaseFragment(), View.OnClickListener {

    companion object {
        const val TAG = "ProducerFragment"
        fun newInstance(): ProducerFragment {
            val args = Bundle()
            val fragment = ProducerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var mTvSentEvent: TextView? = null
    private var mTvSentStickyEvent: TextView? = null
    private var mSubscriberDialogFragment: SubscriberDialogFragment? = null
    override val layoutId: Int
        protected get() = R.layout.fragment_producer

    override fun afterCreate(savedInstanceState: Bundle?) {
        mRootView!!.findViewById<View>(R.id.btn_send_event).setOnClickListener(this)
        mRootView!!.findViewById<View>(R.id.btn_send_sticky_event).setOnClickListener(this)
        mRootView!!.findViewById<View>(R.id.btn_show_dialog).setOnClickListener(this)
        mTvSentEvent = mRootView!!.findViewById<View>(R.id.tv_sent_event) as TextView
        mTvSentStickyEvent = mRootView!!.findViewById<View>(R.id.tv_sent_sticky_event) as TextView
        mSubscriberDialogFragment = com.lsxiao.apollo.demo.fragment.SubscriberDialogFragment.newInstance()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_send_event -> {
                emit(Event.OBJECT, User("lsxiao"))
                mTvSentEvent!!.text = String.format("%sevent,", mTvSentEvent!!.text.toString())
            }
            R.id.btn_send_sticky_event -> {
                emit("sticky", "sticky", true)
                mTvSentStickyEvent!!.text = String.format("%ssticky,", mTvSentStickyEvent!!.text.toString())
            }
            R.id.btn_show_dialog -> {
                if (!mSubscriberDialogFragment?.isAdded!!) {
                    mSubscriberDialogFragment = com.lsxiao.apollo.demo.fragment.SubscriberDialogFragment.newInstance()
                }
                mSubscriberDialogFragment?.show(childFragmentManager, com.lsxiao.apollo.demo.fragment.SubscriberDialogFragment.TAG)
            }
        }
    }
}
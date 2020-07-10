package com.lsxiao.apollo.demo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lsxiao.apollo.core.Apollo.Companion.bind
import com.lsxiao.apollo.core.contract.ApolloBinder

/**
 * author lsxiao
 * date 2016-08-23 19:15
 */
abstract class BaseFragment : Fragment() {
    protected var mRootView: View? = null
    private var mBinder: ApolloBinder? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(layoutId, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinder = bind(this)
        afterCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != mBinder) {
            mBinder!!.unbind()
        }
    }

    protected abstract val layoutId: Int
    protected abstract fun afterCreate(savedInstanceState: Bundle?)

    companion object {
        val TAG = BaseFragment::class.java.simpleName
    }
}
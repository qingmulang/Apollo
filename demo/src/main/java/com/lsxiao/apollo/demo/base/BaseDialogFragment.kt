package com.lsxiao.apollo.demo.base

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.lsxiao.apollo.core.Apollo.Companion.bind
import com.lsxiao.apollo.core.contract.ApolloBinder

/**
 * author lsxiao
 * date 2016-08-23 19:15
 */
abstract class BaseDialogFragment : DialogFragment() {
    protected var mRootView: View? = null
    private var mBinder: ApolloBinder? = null
    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

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
        val TAG = BaseDialogFragment::class.java.simpleName
    }
}
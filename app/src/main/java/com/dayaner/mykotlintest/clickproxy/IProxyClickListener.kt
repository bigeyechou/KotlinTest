package com.dayaner.mykotlintest.clickproxy

import android.view.View

/**
 * -------------------------------------
 * 作者：dayaner
 * -------------------------------------
 * 时间：9/3/21 2:35 PM
 * -------------------------------------
 * 描述：
 * -------------------------------------
 * 备注：
 * -------------------------------------
 */
interface IProxyClickListener {

    fun onProxyClick(wrap: WrapClickListener, v: View): Boolean

    class WrapClickListener(private val mBaseListener: View.OnClickListener, private val mProxyListener: IProxyClickListener) : View.OnClickListener {

        override fun onClick(v: View?) {
            v?.let { view ->
                val handled: Boolean = mProxyListener.onProxyClick(this, view)
                if (handled){
                    mBaseListener.onClick(view)
                }
            }
        }
    }

}
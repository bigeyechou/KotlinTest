package com.dayaner.mykotlintest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dayaner.mykotlintest.clickproxy.IProxyClickListener
import com.dayaner.mykotlintest.clickproxy.IProxyClickListener.WrapClickListener
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() ,IProxyClickListener{
val TAG = "zgzzzz"
    var sHookMethod: Method? = null
    var sHookField: Field? = null
    val mPrivateTagKey = 0

    var mName: String = ""
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = arguments
        mName = args?.get("name") as String
        val rootView: View = inflater.inflate(R.layout.fragment_second, container, false)
        init()
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            hookViews(rootView, 0)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_name).setText(mName)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            Log.i("zgzzzzz", "onViewCreated: 点击 button_second ")
        }
    }

    /**
     * 获取待处理的Method和Field
     */
    @SuppressLint("PrivateApi")
    fun init() {
        if (sHookMethod == null){
            try {
                val viewClass = Class.forName("android.view.View")
                sHookMethod = viewClass.getDeclaredMethod("getListenerInfo")
                sHookMethod?.isAccessible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (sHookField == null){
            try {
                val listenerInfoClass = Class.forName("android.view.View\$ListenerInfo")
                sHookField = listenerInfoClass.getDeclaredField("mOnClickListener")
                sHookField?.isAccessible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hookViews(view: View, recycledContainerDeep: Int) {
        var recycledContainerDeep = recycledContainerDeep
        if (view.visibility == View.VISIBLE) {
            val forceHook = recycledContainerDeep == 1
            if (view is ViewGroup) {
                val existAncestorRecycle = recycledContainerDeep > 0
                val viewGroup: ViewGroup = view
                if (!(viewGroup is AbsListView || viewGroup is RecyclerView) || existAncestorRecycle) {
                    hookClickListener(view, recycledContainerDeep, forceHook)
                    if (existAncestorRecycle) {
                        recycledContainerDeep++
                    }
                } else {
                    recycledContainerDeep = 1
                }
                val childCount = viewGroup.childCount
                repeat((0..childCount).count()-1) {
                    val childView = viewGroup.getChildAt(it)
                    hookViews(childView, recycledContainerDeep)
                }
            } else {
                hookClickListener(view, recycledContainerDeep, forceHook)
            }
        }

    }

    private fun hookClickListener(view: View, recycledContainerDeep: Int, forceHook: Boolean) {
        var needHook = forceHook
        if (!needHook) {
            needHook = view.isClickable
            if (needHook && recycledContainerDeep == 0) {
                needHook = view.getTag(mPrivateTagKey) == null
            }
        }
        if (needHook) {
            try {
                sHookMethod?.let { method ->
                    var getListenerInfo: Any = method.invoke(view)
                    sHookField?.let { field ->
                        val baseClickListener = field.get(getListenerInfo) as View.OnClickListener
                        //获取已设置过的监听器
                        if (baseClickListener !is WrapClickListener) {
                            field[getListenerInfo] = WrapClickListener(baseClickListener, this)
                            view.setTag(mPrivateTagKey, recycledContainerDeep)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onProxyClick(wrap: WrapClickListener, v: View): Boolean {
        Log.i("zgzzzz", "onProxyClick: 拦截 ")
        return true
    }

}
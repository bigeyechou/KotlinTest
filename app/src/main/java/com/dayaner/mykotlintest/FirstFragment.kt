package com.dayaner.mykotlintest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dayaner.mykotlintest.chain.*
import com.dayaner.mykotlintest.data.PersonData

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val args = Bundle()
            args.putString("name","小丽")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,args)
        }
        view.findViewById<Button>(R.id.btn_log).setOnClickListener {
            chainFilter()
        }

        view.findViewById<Button>(R.id.btn_log2).setOnClickListener {
            log()
        }
        val clazz  = Class.forName("com.dayaner.mykotlintest.data.PersonData")
        val filed  = clazz.getDeclaredField("name")
        filed.isAccessible = true
        val value  = filed.get(PersonData())
        Log.i(TAG, "clazz person : " + value)


    }

    val TAG = "zgz"
    private fun log(){

        var i = 1
        i = i++
        Log.i(TAG, "log: i = " + i)
        var j = i++
        Log.i(TAG, "log: i = " + i+ "，j = "+j)
        var k = i + ++i * i++
        Log.i("zgz", "log: i= "+i+",j = "+j+",k = " + k)
    }

    var mThreadLocal : ThreadLocal<Boolean> = ThreadLocal()
    private fun initThreadLocal() {
        mThreadLocal.set(true)
        Log.i("zgz", "ThreadLocal for main : " + mThreadLocal.get())

        run {
            mThreadLocal.set(false)
            Log.i("zgz", "ThreadLocal for thread : " + mThreadLocal.get())
        }

    }

    fun chainFilter(){

        var preparationList = PreparationList()
        preparationList.haveBreak = true
        preparationList.washFace = true
        preparationList.washHair = true

        var washHairFilter = WashHairFilter()
        var washFaceFilter = WashFaceFilter()
        var haveBreakFilter = HaveBreakFilter()

        var study = Study()
        var filterChain = FilterChain(study)
        filterChain.addfilter(washHairFilter)
        filterChain.addfilter(washFaceFilter)
        filterChain.addfilter(haveBreakFilter)

        filterChain.doFilter(preparationList,filterChain)
    }
}
package com.dayaner.mykotlintest.chain

import android.util.Log

/**
 * -------------------------------------
 * 作者：dayaner
 * -------------------------------------
 * 时间：7/1/21 7:20 PM
 * -------------------------------------
 * 描述：
 * -------------------------------------
 * 备注：
 * -------------------------------------
 */
class HaveBreakFilter : StudyPrepareFilter {

    override fun doFilter(preparationList: PreparationList, filterChain: FilterChain) {
       if (preparationList.haveBreak){
           Log.i("zgz", "doFilter: 吃完早饭了")
       }
        filterChain.doFilter(preparationList,filterChain)
    }
}
package com.dayaner.mykotlintest.chain

/**
 * -------------------------------------
 * 作者：dayaner
 * -------------------------------------
 * 时间：7/1/21 4:55 PM
 * -------------------------------------
 * 描述：
 * -------------------------------------
 * 备注：
 * -------------------------------------
 */
interface StudyPrepareFilter {

    fun doFilter(preparationList: PreparationList, filterChain: FilterChain)
}
package com.dayaner.mykotlintest.chain

import android.util.Log


class FilterChain(var study: Study) : StudyPrepareFilter{

//    lateinit var study : Study
    var pos = 0
    var studyPrepareFilterList : ArrayList<StudyPrepareFilter>? = null

    /**
     * 添加过滤的内容
     */
    fun addfilter(studyPrepareFilter: StudyPrepareFilter){
        if (studyPrepareFilterList == null){
            studyPrepareFilterList = ArrayList()
        }
        studyPrepareFilterList?.add(studyPrepareFilter)
    }

    override fun doFilter(thingList: PreparationList, filterChain: FilterChain) {
        //过滤执行完毕
        if (pos == studyPrepareFilterList?.size){
            study.study()
            Log.i("zgz", "doFilter: 开始读书了")
            return
        }
        //没过滤完毕所有，则继续过滤
        studyPrepareFilterList?.get(pos++)?.doFilter(thingList,filterChain)
    }

}

package com.dayaner.mykotlintest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * -------------------------------------
 * 作者：dayaner
 * -------------------------------------
 * 时间：6/30/21 3:12 PM
 * -------------------------------------
 * 描述：
 * -------------------------------------
 * 备注：
 * -------------------------------------
 */
 open class MyWork(context: Context, workerParams: WorkerParameters) :

    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.i("zgz_WorkManager", "doWork: ")
        return Result.success()
    }
}
package com.adnankhan.assignment.base

import androidx.lifecycle.ViewModel
import com.adnankhan.assignment.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    val eventShowHideProgress = SingleLiveEvent<Boolean>()

    fun showLoading() {
        eventShowHideProgress.value = true
    }

    fun hideLoading() {
        eventShowHideProgress.value = false
    }
}
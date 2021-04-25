package com.adnankhan.assignment.base

import androidx.lifecycle.MutableLiveData
import com.adnankhan.assignment.data.model.Drinks
import com.adnankhan.assignment.ui.fragments.home.EnumSearchType
import com.adnankhan.assignment.utils.SingleLiveEvent

class SharedViewModel : BaseViewModel() {

    val eventFetchDrinksByName = SingleLiveEvent<String>()
    val eventFetchDrinksByAlphabet = SingleLiveEvent<String>()
    val eventResultDrinksByName = SingleLiveEvent<List<Drinks>>()
    val eventDrinkFavorite = SingleLiveEvent<Drinks>()
    val liveFavorites = MutableLiveData<List<Drinks>>()
    var enumSearchType = EnumSearchType.NAME
}
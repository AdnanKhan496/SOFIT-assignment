package com.adnankhan.assignment.ui.fragments.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.adnankhan.assignment.BR
import com.adnankhan.assignment.R
import com.adnankhan.assignment.base.BaseFragment
import com.adnankhan.assignment.data.model.Drinks
import com.adnankhan.assignment.databinding.FragmentHomeBinding
import com.adnankhan.assignment.ui.fragments.home.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var adapter: HomeAdapter
    private val list = mutableListOf<Drinks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = HomeAdapter(list, object : HomeAdapter.OnClickListener {
            override fun onClickFav(drinks: Drinks) {
                sharedViewModel.eventDrinkFavorite.value = drinks
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeShareViewModel()
        rvDrinks.adapter = adapter
        lifecycleScope.launch {
            when (dataStoreProvider.type.first()) {
                EnumSearchType.NAME.name -> {
                    rbName.isChecked = true
                    sharedViewModel.eventFetchDrinksByName.value = "margarita"
                }
                EnumSearchType.ALPHABET.name -> {
                    rbAlphabet.isChecked = true
                    sharedViewModel.eventFetchDrinksByAlphabet.value = "m"
                }
            }
        }
        rgSearch.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbName) {
                sharedViewModel.enumSearchType = EnumSearchType.NAME
                lifecycleScope.launch {
                    dataStoreProvider.saveState(EnumSearchType.NAME.name)
                }
            } else {
                sharedViewModel.enumSearchType = EnumSearchType.ALPHABET
                lifecycleScope.launch {
                    dataStoreProvider.saveState(EnumSearchType.ALPHABET.name)
                }
            }
        }
        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                when (sharedViewModel.enumSearchType) {
                    EnumSearchType.NAME -> sharedViewModel.eventFetchDrinksByName.value =
                        etSearch.text.toString()

                    EnumSearchType.ALPHABET -> sharedViewModel.eventFetchDrinksByAlphabet.value =
                        etSearch.text.toString()
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun observeShareViewModel() {
        sharedViewModel.eventResultDrinksByName.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }
}

enum class EnumSearchType {
    NAME,
    ALPHABET
}
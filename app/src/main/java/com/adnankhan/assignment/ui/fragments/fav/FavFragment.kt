package com.adnankhan.assignment.ui.fragments.fav

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.adnankhan.assignment.BR
import com.adnankhan.assignment.R
import com.adnankhan.assignment.base.BaseFragment
import com.adnankhan.assignment.data.model.Drinks
import com.adnankhan.assignment.databinding.FragmentFavBinding
import com.adnankhan.assignment.ui.fragments.fav.adapter.FavAdapter
import kotlinx.android.synthetic.main.fragment_fav.*

class FavFragment : BaseFragment<FragmentFavBinding, FavViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_fav
    override val viewModel: Class<FavViewModel>
        get() = FavViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private val list = mutableListOf<Drinks>()
    private lateinit var adapter: FavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavAdapter(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeShareViewModel()
    }

    private fun observeShareViewModel() {
        sharedViewModel.liveFavorites.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    private fun init() {
        rvFavorites.adapter = adapter
    }
}
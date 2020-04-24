package com.ulusofona.aula_5.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.ulusofona.aula_5.*
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.ui.adapters.HistoryAdapter
import com.ulusofona.aula_5.ui.listeners.ItemLongClickListener
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import com.ulusofona.aula_5.ui.utils.NavigationManager
import com.ulusofona.aula_5.ui.viewmodels.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_historic.list_historic

class HistoricFragment : Fragment(), OnHistoryChanged, ItemLongClickListener {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStorageChanged(value: List<Operation>?) {
        value?.let {
            list_historic?.layoutManager = LinearLayoutManager(activity as Context)
            list_historic?.adapter = HistoryAdapter(
                activity as Context,
                R.layout.item_expression,
                it.toMutableList() as ArrayList<Operation>,
                this
            )
        }
    }

    override fun onLongClick(item: Operation): Boolean {
        historyViewModel.onLongClick(item)
        return true
    }

    @OnClick(R.id.button_back)
    fun onClickBack(view: View) {
        NavigationManager.goToCalculatorFragment(
            requireFragmentManager()
        )
    }

    override fun onStart() {
        historyViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        historyViewModel.unregisterListener()
        super.onDestroy()
    }

}

package com.ulusofona.aula_5

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import kotlinx.android.synthetic.main.fragment_historic.*

class CalculatorFragment : Fragment(), OnDisplayChanged, OnHistoryChanged, ItemLongClickListener {
    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        calculatorViewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        calculatorViewModel.registerListener(this)
        historyViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let { text_visor.text = it }
    }

    override fun onStorageChanged() {
        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            historyViewModel.storage.getAll().toMutableList() as ArrayList<Operation>,
            this
        )
    }

    override fun onDestroy() {
        calculatorViewModel.unregisterListener()
        super.onDestroy()
    }

    @OnClick(R.id.button_Clear)
    fun onClickClear() {
        calculatorViewModel.onClickClear()
    }

    @OnClick(R.id.button_BackSpace)
    fun onClickBackSpace() {
        calculatorViewModel.onClickBackSpace()
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals() {
        calculatorViewModel.onClickEquals().toString()
        historyViewModel.onClickEquals()
    }

    @Optional
    @OnClick(
        R.id.button_0,
        R.id.button_00,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9,
        R.id.button_adition,
        R.id.button_divide,
        R.id.button_dot,
        R.id.button_minus,
        R.id.button_mult
    )
    fun onClickSymbol(view: View) {
        calculatorViewModel.onClickSymbol(view.tag.toString())
    }

    @Optional
    @OnClick(R.id.nav_history)
    fun onClickHistory(view: View) {
        NavigationManager.goToHistoricFragment(requireFragmentManager())
    }

    override fun onLongClick(item: Operation): Boolean {return true}
}

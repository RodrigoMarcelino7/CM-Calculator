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
import butterknife.Optional
import com.ulusofona.aula_5.*
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.ui.adapters.HistoryAdapter
import com.ulusofona.aula_5.ui.listeners.ItemLongClickListener
import com.ulusofona.aula_5.ui.listeners.OnDisplayChanged
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import com.ulusofona.aula_5.ui.utils.NavigationManager
import com.ulusofona.aula_5.ui.viewmodels.CalculatorViewModel
import com.ulusofona.aula_5.ui.viewmodels.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.view.*

class CalculatorFragment : Fragment(),
    OnDisplayChanged,
    OnHistoryChanged,
    ItemLongClickListener {
    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        calculatorViewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        context?.let { calculatorViewModel.registerListener(this, this, it) }
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let { text_visor.text = it }
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
        context?.let { calculatorViewModel.onClickEquals(it) }
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
    fun onClickHistory() {
        NavigationManager.goToHistoricFragment(
            requireFragmentManager()
        )
    }

    override fun onLongClick(): Boolean {
        return true
    }
}

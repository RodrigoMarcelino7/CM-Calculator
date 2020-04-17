package com.ulusofona.aula_5

import android.bluetooth.BluetoothDevice.EXTRA_NAME
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnLongClick
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_historic.*
import kotlinx.android.synthetic.main.fragment_historic.list_historic

class HistoricFragment : Fragment(), OnHistoryChanged, ItemLongClickListener {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    override fun onStorageChanged() {
        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            historyViewModel.storage.getAll().toMutableList() as ArrayList<Operation>,
            this
        )
    }

    override fun onLongClick(item: Operation): Boolean {
        historyViewModel.onLongClick(item)
        return true
    }

    @OnClick(
        R.id.button_back
    )
    fun onClickBack(view: View) {
        NavigationManager.goToCalculatorFragment(requireFragmentManager())
    }

    override fun onStart() {
        historyViewModel.registerListener(this)
        super.onStart()
    }

}

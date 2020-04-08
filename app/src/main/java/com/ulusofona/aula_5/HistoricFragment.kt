package com.ulusofona.aula_5

import android.bluetooth.BluetoothDevice.EXTRA_NAME
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_historic.*

class HistoricFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(activity as Context, MainActivity::class.java)
        val historic = intent.getParcelableArrayListExtra<Operation>(EXTRA_NAME)

        historic?.let {
            list_historic.layoutManager = LinearLayoutManager(activity as Context)
            list_historic.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, historic)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(
        R.id.button_back
    )
    fun onClickBack(view :View){
        NavigationManager.goToCalculatorFragment(requireFragmentManager())
    }
}

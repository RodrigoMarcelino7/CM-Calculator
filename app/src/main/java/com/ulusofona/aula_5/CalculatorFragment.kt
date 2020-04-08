package com.ulusofona.aula_5

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {
    private val TAG = MainActivity::class.java.simpleName

    private var historic = arrayListOf(Operation("1+1","2"), Operation("2+3","5"))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @Optional
    @OnClick(R.id.button_Clear)
    fun onClickClear(view: View){
        Log.i(TAG,"Click no botão c")
        text_visor.text = "0"
    }

    @Optional
    @OnClick(R.id.button_BackSpace)
    fun onClickBackSpace(view: View){
        Log.i(TAG,"Click no botão <")
        if(text_visor.text.length > 1)
            text_visor.text = text_visor.text.substring(0,text_visor.length()-1)
        else
            text_visor.text = "0"
    }

    @Optional
    @OnClick(R.id.button_equals)
    fun onClickEquals(view: View){
        val configuration: Configuration = resources.configuration
        Log.i(TAG,"Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        historic.add(0,Operation(text_visor.text.toString(), expression.evaluate().toString()))
        text_visor.text = expression.evaluate().toString()
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list_historic.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, historic)
        }
        Log.i(TAG,"O resultado da expressão é ${text_visor.text}")
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
    fun onClickSymbol(view: View){
        val symbol = view.tag.toString()
        Log.i(TAG, "Click no botão $symbol")
        if(text_visor.text.toString() == "0"){
            text_visor.text = symbol
        }
        else{
            text_visor.append(symbol)
        }
    }

    @Optional
    @OnClick(R.id.nav_history)
    fun onClickHistory(view: View){
        NavigationManager.goToHistoricFragment(requireFragmentManager())
    }
}

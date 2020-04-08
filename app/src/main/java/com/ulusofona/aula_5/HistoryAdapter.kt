package com.ulusofona.aula_5

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expression.view.*

class HistoryAdapter (private val context: Context, private val layout: Int, private val items: MutableList<Operation>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val expression: TextView = view.text_expression
        val result: TextView = view.text_result
        val image: ImageView = view.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HistoryViewHolder{
        return HistoryViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int){
        holder.image.setImageResource(R.drawable.logo)
        holder.expression.text = items[position].expression
        holder.result.text = items[position].result.toString()
    }

    override  fun getItemCount() = items.size

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = convertView ?: LayoutInflater.from(context).inflate(layout, parent, false)
//        val expressionParts = getItem(position)
//        view.image.setImageResource(R.drawable.logo)
//        view.text_expression.text = expressionParts!!.expression
//        view.text_result.text = expressionParts.result
//        return view
//    }
}
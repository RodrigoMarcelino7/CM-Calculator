package com.ulusofona.aula_5.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulusofona.aula_5.ui.listeners.ItemLongClickListener
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.R
import kotlinx.android.synthetic.main.item_expression.view.*

class HistoryAdapter(
    private val context: Context,
    private val layout: Int,
    private val items: MutableList<Operation>,
    private var clickListener: ItemLongClickListener
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val expression: TextView = view.text_expression
        val result: TextView = view.text_result
        val image: ImageView = view.image

        fun initialize(item: Operation, listener: ItemLongClickListener){
            expression.text = item.expression
            result.text =item.result.toString()
            image.setImageResource(R.drawable.logo)
            itemView.setOnLongClickListener{
                listener.onLongClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(context).inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.initialize(items[position],clickListener)
    }

    override fun getItemCount() = items.size
}
package com.example.owner.rolecall.ui.bluetoothclass

import android.bluetooth.BluetoothClass
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
//import android.support.v7.app.AlertController
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.owner.rolecall.R
import com.example.owner.rolecall.data.Bluetoothclass


//Adapts list of Alert model objects to a list of rows on UI
class ClassAdapter(
    private val alerts: List<Bluetoothclass>,
    private val rowClickListener: OnRowClickListener
) : RecyclerView.Adapter<ClassAdapter.ViewHolder>(){

    interface OnRowClickListener{
        fun onRowItemClicked(alert: Bluetoothclass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem_bluetoothclass, parent,false)
        return ViewHolder(view)
    }

    // Total rows to render
    override fun getItemCount(): Int{
        return alerts.size
    }

    /**
     * The list is ready to render a new row at [position]. It gives you the [ViewHolder]
     * either created from [onCreateViewHolder] or recycled from a row that scrolled offscreen.
     * So, you need to set up the content of the row's UI based on corresponding [Alert].
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBluetoothclass = alerts[position]
        holder.titleTextView.text = currentBluetoothclass.name
        holder.contentTextView.text = currentBluetoothclass.host

        holder.cardView.setOnClickListener {
            rowClickListener.onRowItemClicked(currentBluetoothclass)
        }
    }

    /**
     * Holds a reference to the views of a row that has already been loaded (inflated) from XML.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardView: CardView = view.findViewById(R.id.cardView)

        val titleTextView: TextView = view.findViewById(R.id.title)

        val contentTextView: TextView = view.findViewById(R.id.class_content)

    }
}
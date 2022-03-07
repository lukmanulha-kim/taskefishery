package com.lukman.taskefishery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lukman.taskefishery.data.ResponseHargaItem

class HargaAdapter(val data: List<ResponseHargaItem>?): RecyclerView.Adapter<HargaAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HargaAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_harga, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: HargaAdapter.MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val komoditi = itemView.findViewById<TextView>(R.id.komoditi)
        private val kabupaten = itemView.findViewById<TextView>(R.id.kabupaten)
        private val ukuran = itemView.findViewById<TextView>(R.id.ukuran)
        private val harga = itemView.findViewById<TextView>(R.id.harga)
        fun bind(get: ResponseHargaItem?) {
            komoditi.text = get?.komoditas
            kabupaten.text = get?.areaKota
            ukuran.text = get?.size
            harga.text = get?.price
        }
    }
}
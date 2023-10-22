package dev.ndridm.rtkeluhanapp.ressiskamling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ndridm.rtkeluhanapp.databinding.ItemSiskamlingBinding

class SisAdapter(private val list: ArrayList<ResponseSiskamling>): RecyclerView.Adapter<SisAdapter.SisViewHolder>(){

    inner class SisViewHolder(val itemSiskamlingBinding: ItemSiskamlingBinding): RecyclerView.ViewHolder(itemSiskamlingBinding.root){
        fun bindItem(responseSiskamling: ResponseSiskamling){
            itemSiskamlingBinding.tvTgl.text = responseSiskamling.tgl
            itemSiskamlingBinding.tvJumlah.text = responseSiskamling.jumlah
            itemSiskamlingBinding.tvRonda1.text = responseSiskamling.ronda_1
            itemSiskamlingBinding.tvRonda2.text = responseSiskamling.ronda_2
            itemSiskamlingBinding.tvRonda3.text = responseSiskamling.ronda_3
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SisAdapter.SisViewHolder {
       return SisViewHolder(ItemSiskamlingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SisAdapter.SisViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size

}
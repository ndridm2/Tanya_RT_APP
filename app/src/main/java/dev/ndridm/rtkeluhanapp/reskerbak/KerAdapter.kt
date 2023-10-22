package dev.ndridm.rtkeluhanapp.reskerbak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ndridm.rtkeluhanapp.databinding.ItemKerbakBinding

class KerAdapter(private val list: ArrayList<ResponseKerbak>): RecyclerView.Adapter<KerAdapter.KerViewHolder>() {

    inner class KerViewHolder(val itemKerbakBinding: ItemKerbakBinding): RecyclerView.ViewHolder(itemKerbakBinding.root){
        fun kerItem(responseKerbak: ResponseKerbak){
            itemKerbakBinding.tvTgl.text = responseKerbak.tgl
            itemKerbakBinding.tvKegiatan.text = responseKerbak.kegiatan
            itemKerbakBinding.tvLokasi.text = responseKerbak.lokasi
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KerAdapter.KerViewHolder {
        return KerViewHolder(ItemKerbakBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: KerAdapter.KerViewHolder, position: Int) {
        holder.kerItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}
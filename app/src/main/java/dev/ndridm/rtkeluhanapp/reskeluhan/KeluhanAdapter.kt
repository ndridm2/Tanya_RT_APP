package dev.ndridm.rtkeluhanapp.reskeluhan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ndridm.rtkeluhanapp.databinding.ItemAdminKeluhanBinding

class KeluhanAdapter(private val list: ArrayList<ResponseKeluhan>): RecyclerView.Adapter<KeluhanAdapter.KelViewHolder>() {

    inner class KelViewHolder(val itemAdminKeluhanBinding: ItemAdminKeluhanBinding): RecyclerView.ViewHolder(itemAdminKeluhanBinding.root){
        fun kelItem(responseKeluhan: ResponseKeluhan){
            itemAdminKeluhanBinding.tvNama.text = responseKeluhan.nama
            itemAdminKeluhanBinding.tvRumah.text = responseKeluhan.rumah
            itemAdminKeluhanBinding.tvRt.text = responseKeluhan.rt
            itemAdminKeluhanBinding.tvKel.text = responseKeluhan.jenis_keluhan
            itemAdminKeluhanBinding.tvKet.text = responseKeluhan.ket
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KeluhanAdapter.KelViewHolder {
        return KelViewHolder(ItemAdminKeluhanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: KeluhanAdapter.KelViewHolder, position: Int) {
        holder.kelItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}
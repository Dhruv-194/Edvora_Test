package com.dhruv194.edvora.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhruv194.edvora.databinding.ActivityMainBinding
import com.dhruv194.edvora.databinding.CardItemBinding
import com.dhruv194.edvora.dataclasses.ComparatorTwo
import com.dhruv194.edvora.dataclasses.RidesDataClass
import com.dhruv194.edvora.fragments.TAG
import com.dhruv194.edvora.interfaces.RidesInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NearestRidesAdapter : RecyclerView.Adapter<NearestRidesAdapter.NearestRidesViewHolder>() {

    inner class NearestRidesViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBAck = object : DiffUtil.ItemCallback<RidesDataClass>(){
        override fun areItemsTheSame(oldItem: RidesDataClass, newItem: RidesDataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RidesDataClass, newItem: RidesDataClass): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBAck)
    var nearestrides : List<RidesDataClass>
        get() = differ.currentList
        set(value)  {differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearestRidesViewHolder {
        return NearestRidesViewHolder(CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
 /* lateinit var stationid :String*/

    override fun onBindViewHolder(holder: NearestRidesViewHolder, position: Int) {

       /* CoroutineScope(Dispatchers.IO).launch {
            var response = RidesInstance.api.getUser()
            stationid = response.body()!!.stationCode.toString()
        }*/


        holder.binding.apply {
                val nrides = nearestrides[position]
            itemCityName.text = nrides.city
            itemStateName.text = nrides.state
            itemRideId.text = nrides.id.toString()
            itemOriginStation.text = nrides.originStationCode.toString()
            itemStationPath.text = nrides.stationPath.toString()
            itemDate.text = nrides.date


        }
    }

    override fun getItemCount() = nearestrides.size


}
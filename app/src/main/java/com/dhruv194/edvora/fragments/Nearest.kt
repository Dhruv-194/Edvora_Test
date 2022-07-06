package com.dhruv194.edvora.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.edvora.ConfigFile
import com.dhruv194.edvora.R
import com.dhruv194.edvora.ViewModel
import com.dhruv194.edvora.adapters.NearestRidesAdapter
import com.dhruv194.edvora.databinding.FragmentNearestBinding
import com.dhruv194.edvora.dataclasses.ComparatorOne
import com.dhruv194.edvora.dataclasses.ComparatorTwo

import com.dhruv194.edvora.dataclasses.RidesDataClass
import com.dhruv194.edvora.interfaces.RidesInstance
import okhttp3.internal.filterList
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val TAG = "FRAGMENT A"

class Nearest : Fragment() {

    private lateinit var binding: FragmentNearestBinding

    private lateinit var nearestrideAdapter: NearestRidesAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response= try {
                RidesInstance.api.getRide()
            }catch (e:IOException){
                Log.e(TAG,"onCreate: no internet")
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.e(TAG,"onCreate: unexpected http 404")
                return@launchWhenCreated
            }

            if (response.isSuccessful&&response.body() !=null){
                nearestrideAdapter.nearestrides = response.body()!!
            }else{
                Log.e(TAG,"onCreate: response not successful")
            }
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNearestBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

        lifecycleScope.launchWhenCreated {

            val response= try {
                RidesInstance.api.getRide()
            }catch (e: IOException){
                Log.e(TAG,"onCreate: no internet"+e.message+e.stackTraceToString())
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.e(TAG,"onCreate: unexpected http 404")
                return@launchWhenCreated
            }

            if (response.isSuccessful&&response.body() !=null){
          //var nearRides =  ArrayList<RidesDataClass>()
              /*  val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm aa")


            response.body()!!.forEach {
                if (sdf.parse(it.date) > sdf.parse("03/24/2022 10:58 AM")) {
                    upcomingRides.add(it)
                }
            }*/
                nearestrideAdapter.nearestrides = response.body()!!.sortedWith(ComparatorTwo(ConfigFile.userstaioncode))


            }else{
                Log.e(TAG,"onCreate: response not successful")
            }
        }
    }

    private fun setupRecyclerView() = binding.nearestRv.apply {

        nearestrideAdapter = NearestRidesAdapter()
        adapter =  nearestrideAdapter
        layoutManager = LinearLayoutManager(context)

    }

}
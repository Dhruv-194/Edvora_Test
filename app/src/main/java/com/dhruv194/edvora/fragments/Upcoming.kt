package com.dhruv194.edvora.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruv194.edvora.ConfigFile
import com.dhruv194.edvora.R
import com.dhruv194.edvora.ViewModel
import com.dhruv194.edvora.adapters.NearestRidesAdapter
import com.dhruv194.edvora.adapters.UpcomingRidesAdapter
import com.dhruv194.edvora.databinding.FragmentUpcomingBinding
import com.dhruv194.edvora.dataclasses.ComparatorOne
import com.dhruv194.edvora.dataclasses.ComparatorTwo
import com.dhruv194.edvora.dataclasses.RidesDataClass
import com.dhruv194.edvora.interfaces.RidesInstance
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat

const val TAG1 = "FRAGMENT A"
class Upcoming : Fragment() {
    private lateinit var binding: FragmentUpcomingBinding
    private lateinit var upcomingrideAdapter: UpcomingRidesAdapter
    private val viewModel : ViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {

            val response1= try {
                RidesInstance.api.getRide()
            }catch (e: IOException){
                Log.e(TAG1,"onCreate: no internet"+e.message+e.stackTraceToString())
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.e(TAG1,"onCreate: unexpected http 404")
                return@launchWhenCreated
            }

            if (response1.isSuccessful&&response1.body() !=null){
                var upcomingRides =  ArrayList<RidesDataClass>()
                val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm aa")


                response1.body()!!.forEach {
                    if (sdf.parse(it.date) > sdf.parse("03/24/2022 10:58 AM")) {
                        upcomingRides.add(it)

                    }
                }
                upcomingrideAdapter.upcomingrides = upcomingRides.sortedWith(ComparatorOne())
                Toast.makeText(context,"Upcoming Rides:"+upcomingRides.size.toString(),Toast.LENGTH_SHORT).show()
                viewModel.selectItem(upcomingRides.size.toString())
            }else{
                Log.e(TAG,"onCreate: response not successful")
            }
        }
    }

    private fun setupRecyclerView() = binding.upcomingRv.apply {

        upcomingrideAdapter = UpcomingRidesAdapter()
        adapter =  upcomingrideAdapter
        layoutManager = LinearLayoutManager(context)

    }

}
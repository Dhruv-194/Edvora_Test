package com.dhruv194.edvora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dhruv194.edvora.databinding.ActivityMainBinding
import com.dhruv194.edvora.dataclasses.UserDataClass
import com.dhruv194.edvora.fragments.TAG
import com.dhruv194.edvora.interfaces.RidesInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewPagerAdapter  : ViewPagerAdapter

    private val viewModel : ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        hideactionbar()

        lateinit var upcomingR : String
        lateinit var pastR : String
        viewModel.getSelectedItem()?.observe(this, Observer {
           // Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            upcomingR = it
            binding.tablayout.getTabAt(1)?.text = "Upcoming($upcomingR)"

        })

        viewModel.getSelectedItem1()?.observe(this, Observer {
            pastR = it
            binding.tablayout.getTabAt(2)?.text = "Past($pastR)"
        })

        viewPagerAdapter =  ViewPagerAdapter(this)
        binding.viewpager.adapter = viewPagerAdapter


        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tablayout.getTabAt(position)!!.select()
            }
        })

        lifecycleScope.launchWhenCreated {
            val response= try {
                RidesInstance.api.getUser()
            }catch (e: IOException){
                Log.e(TAG,"onCreate: no internet")
                return@launchWhenCreated
            }catch (e : HttpException){
                Log.e(TAG,"onCreate: unexpected http 404")
                return@launchWhenCreated
            }

            if (response.isSuccessful&&response.body() !=null){
                ConfigFile.username = response.body()!!.name
                ConfigFile.userstaioncode = response.body()!!.stationCode
                binding.userName.text = ConfigFile.username+ " : " + ConfigFile.userstaioncode.toString()
            }else{
                Log.e(TAG,"onCreate: response not successful")
            }
        }

    }

    private fun hideactionbar() {
        supportActionBar?.hide()
    }

}
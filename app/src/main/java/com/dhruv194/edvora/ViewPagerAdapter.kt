package com.dhruv194.edvora

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dhruv194.edvora.fragments.Nearest
import com.dhruv194.edvora.fragments.Past
import com.dhruv194.edvora.fragments.Upcoming

 class ViewPagerAdapter( fragmentActivity: FragmentActivity) :
     FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> Nearest()
            1-> Upcoming()
            2-> Past()
            else->{
                Nearest()
            }
        }
    }
}
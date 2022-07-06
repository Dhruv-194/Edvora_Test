package com.dhruv194.edvora

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel(){
    private var selectedItem = MutableLiveData<String>()
    private var selectedItem1 = MutableLiveData<String>()
    fun selectItem(string: String) {
        selectedItem.value = string
    }

    fun selectItem1(string: String){
        selectedItem1.value = string
    }

    fun getSelectedItem(): LiveData<String>? {
        return selectedItem
    }

    fun getSelectedItem1(): LiveData<String>?{
        return selectedItem1
    }
   /* private val mutableLiveData = MutableLiveData<String>()
    val selectedString : LiveData<String>get() = mutableLiveData
       *//* set(string: LiveData<String>) {
            mutableLiveData.value(string)
        }*//*

    fun selectString(string: String){
        mutableLiveData.value = string
    }*/
}



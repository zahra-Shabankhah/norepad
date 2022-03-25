package com.example.notepad.fragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.notepad.R
import com.example.notepad.data.models.ToDoData
import com.example.notepad.fragment.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {
    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: View, navigate:Boolean){
            view.setOnClickListener {
                if (navigate){
                     view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDb")
        @JvmStatic
        fun emptyDb(view:View,emptyDb:MutableLiveData<Boolean>){
            when(emptyDb.value){
                true-> view.visibility = View.VISIBLE
                false-> view.visibility= View.INVISIBLE
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view:View, currentItem :ToDoData){
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }

    }
}
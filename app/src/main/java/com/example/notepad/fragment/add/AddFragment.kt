package com.example.notepad.fragment.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notepad.R
import com.example.notepad.data.SharedViewModel
import com.example.notepad.data.models.Priority
import com.example.notepad.data.models.ToDoData
import com.example.notepad.data.viewModel.ToDoViewModel
import com.example.notepad.databinding.FragmentAddBinding
import com.example.notepad.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {
    private val mToDoViewModel: ToDoViewModel by viewModels()

    private val mSharedViewModel : SharedViewModel by viewModels()

    //private var _binding: FragmentAddBinding? = null
   // private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // _binding = FragmentAddBinding.inflate(inflater, container, false)



        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.menu_add){
            insertDataToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle =  title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle,mDescription)
        if (validation){
            //insert data to database
            val newData = ToDoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Please fill all field", Toast.LENGTH_SHORT).show()
        }
    }


}
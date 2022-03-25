package com.example.notepad.fragment.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notepad.R
import com.example.notepad.data.SharedViewModel
import com.example.notepad.data.models.ToDoData
import com.example.notepad.data.viewModel.ToDoViewModel
import com.example.notepad.databinding.FragmentListBinding
import com.example.notepad.databinding.FragmentUpdateBinding
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private val mToDoViewModel : ToDoViewModel by viewModels()
    private val mSharedViewModel : SharedViewModel by viewModels()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args
        val view = binding.root


        setHasOptionsMenu(true)

       // view.current_title_et.setText(args.currentItem.title)
       // view.current_description_et.setText(args.currentItem.description)


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> removeItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeItem() {
        mToDoViewModel.DeleteData(args.currentItem)
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun updateItem() {
       val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val validation = mSharedViewModel.verifyDataFromUser(title,description)
        if (validation){
            val updatedItem = ToDoData(
                args.currentItem.id,title,mSharedViewModel.parsePriority("HIGH"),description
            )
            mToDoViewModel.updateData(updatedItem)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "please fill all field", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
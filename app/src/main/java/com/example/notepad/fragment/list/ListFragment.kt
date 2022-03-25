package com.example.notepad.fragment.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.R
import com.example.notepad.data.SharedViewModel
import com.example.notepad.data.viewModel.ToDoViewModel
import com.example.notepad.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private val mToDoViewModel : ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val adapter : ListAdapter by lazy { ListAdapter() }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = this
        binding.mSharedViewModel= mSharedViewModel

        setUpRecyclerView()

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDbEmpty(data)
            adapter.setData(data)
        })

       /* mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDbViews(it)
        })*/

        setHasOptionsMenu(true)

        return view
    }

    private fun setUpRecyclerView() {
        val recyclerView= binding.recyclerView
        recyclerView.adapter= adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

    }

 /*   private fun showEmptyDbViews(emptyDatabase:Boolean) {
        if (emptyDatabase){
             no_data_imageView.visibility = View.VISIBLE
            no_data_textView.visibility = View.VISIBLE
        }else {
            no_data_imageView.visibility = View.INVISIBLE
            no_data_textView.visibility = View.INVISIBLE
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete_all -> mToDoViewModel.DeleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
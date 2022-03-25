package com.example.notepad.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.data.models.ToDoData
import com.example.notepad.databinding.RowLayoutBinding
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var dataList = emptyList<ToDoData>()

    //before live data:
    // class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    //after live data
    class MyViewHolder(private val binding:RowLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       // return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))

       //after live data
        return MyViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //after live data
        val currentItem = dataList[position]
        holder.bind(currentItem)
  /*      holder.itemView.title_txt.text = dataList[position].title
        holder.itemView.description_txt.text = dataList[position].description
        holder.itemView.row_background.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil = ToDoDiffUtil(dataList,toDoData)
        val toDODiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
       toDODiffResult.dispatchUpdatesTo(this)
    }
}

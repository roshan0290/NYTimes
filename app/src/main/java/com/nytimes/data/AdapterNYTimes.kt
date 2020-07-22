package com.nytimes.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.R
import com.nytimes.data.response.GetMasters
import com.nytimes.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class AdapterNYTimes (
    private val context: Context,
    private val listNotification: ArrayList<GetMasters.Result>?,
    listener: OnClickAction,
    itemlistener : OnItemClick
) : RecyclerView.Adapter<AdapterNYTimes.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    lateinit var binding: ItemListBinding
    var listener : OnClickAction = listener
    var itemlistener : OnItemClick = itemlistener

    interface OnClickAction {
        fun onClickAction(position: Int, item: ArrayList<GetMasters.Result>)
    }

    interface OnItemClick {
        fun onItemClick(position: Int, value : TextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)

        holder.itemView.setOnClickListener {


            listener.onClickAction(position,listNotification!!)

        }
        /* holder.binding.layoutEdit.setOnClickListener {

             itemlistener.onItemClick(position,"edit")

         }
         holder.binding.imgDown.setOnClickListener {

             itemlistener.onItemClick(position,"delete")

         }
 */

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return listNotification!!.size
    }

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NewApi")
        fun bindData(position: Int) {

            if(!listNotification.isNullOrEmpty())
            {
                binding.data = listNotification[adapterPosition]
                Picasso.get().load(listNotification[adapterPosition].
                media[0].mediametadata[1].url).into(binding.imgIcon)
            }
            else
            {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                listNotification?.clear()

            }

            //notifyDataSetChanged()
        }



    }
}
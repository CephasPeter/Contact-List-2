package com.zuri.contact_list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zuri.contact_list.databinding.ContactListItemBinding
import com.zuri.contact_list.databinding.HomeListItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val category = mutableListOf<Category>()
    private lateinit var context:Context;

    inner class CategoryViewHolder(private val binding: HomeListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindItem(category: Category){
            binding.imageView.setImageResource(category.drawable)
            binding.textView.text = category.title
            binding.parent.setOnClickListener {
                val intent = Intent(context,ContactActivity::class.java)
                intent.putExtra("Title",category.title)
                context.startActivity(intent)
            }
        }
    }

    fun setupContacts(contacts: List<Category>,context: Context){
        this.category.addAll(contacts)
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(HomeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder:CategoryViewHolder, position: Int) {
        val category = category[position]
        holder.bindItem(category)
    }
}
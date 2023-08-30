package com.nazmul.notesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nazmul.notesapp.databinding.NotesLayoutItemBinding
import com.nazmul.notesapp.model.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        val binding = NotesLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }
    private val DiffUtilCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
              return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, DiffUtilCallBack)

    inner class NotesAdapterViewHolder(private val binding : NotesLayoutItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note : Note){
            binding.apply {
                tvTitle.text = note.title
                tvDescription.text = note.description
            }
        }
    }
}
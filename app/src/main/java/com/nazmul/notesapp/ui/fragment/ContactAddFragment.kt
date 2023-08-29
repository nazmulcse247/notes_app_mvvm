package com.nazmul.notesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nazmul.notesapp.R
import com.nazmul.notesapp.databinding.FragmentContactAddBinding
import com.nazmul.notesapp.model.Note
import com.nazmul.notesapp.utils.CoroutineUtils.executeInCoroutine
import com.nazmul.notesapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactAddFragment : Fragment() {
    private lateinit var binding: FragmentContactAddBinding
    private val noteViewModel : NoteViewModel by viewModels()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNotes.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val note = Note(
                id = id,
                title = title,
                description = description
            )
            executeInCoroutine {
                noteViewModel.saveTask(note)
                id++
            }

        }
    }

}
package com.nazmul.notesapp.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nazmul.notesapp.R
import com.nazmul.notesapp.databinding.FragmentNotesListBinding
import com.nazmul.notesapp.utils.CoroutineUtils.executeInCoroutine
import com.nazmul.notesapp.utils.Resource
import com.nazmul.notesapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment : Fragment() {

    private lateinit var binding: FragmentNotesListBinding
    private val viewModel : NoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        getAllNoteUIObserver()

    }

    private fun getAllNoteUIObserver() {
        executeInCoroutine {
            viewModel.notes.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Log.d("room", "list of notes: ${it.data?.size}")
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "list of notes: ${it.data?.size}")
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.apply {
            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_notesListFragment_to_contactAddFragment)
            }
        }
    }

}
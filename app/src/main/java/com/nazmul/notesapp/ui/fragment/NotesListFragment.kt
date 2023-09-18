package com.nazmul.notesapp.ui.fragment
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nazmul.notesapp.R
import com.nazmul.notesapp.adapters.NotesAdapter
import com.nazmul.notesapp.databinding.FragmentNotesListBinding
import com.nazmul.notesapp.ui.base.BaseFragment
import com.nazmul.notesapp.utils.CoroutineUtils.executeInCoroutine
import com.nazmul.notesapp.utils.Resource
import com.nazmul.notesapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment : BaseFragment<FragmentNotesListBinding>() {


    private val viewModel : NoteViewModel by viewModels()
    private val notesAdapter by lazy { NotesAdapter() }

    override fun viewBindingLayout(): FragmentNotesListBinding = FragmentNotesListBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        showToastMessage("OnCreateview")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToastMessage("OnViewCreated")

        setOnClickListener()
        getAllNoteUIObserver()

    }

    private fun getAllNoteUIObserver() {
        executeInCoroutine {
            viewModel.notes.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "getAllNoteUIObserver"+it.data)
                        Toast.makeText(requireContext(), "${it.data!!.size}", Toast.LENGTH_SHORT).show()
                        notesAdapter.differ.submitList(it.data)
                        binding.rvNote.adapter = notesAdapter


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
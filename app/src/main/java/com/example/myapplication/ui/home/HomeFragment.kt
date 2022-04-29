package com.example.myapplication.ui.home

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.IMessageRVAdapter
import com.example.myapplication.MessageAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.roomDatabase.Message

class HomeFragment : Fragment(), IMessageRVAdapter {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MessageAdapter(this)
        binding.recyclerView.adapter = adapter

        homeViewModel.allMessages.observe(viewLifecycleOwner) { List ->
            adapter.updateList(List)
        }

        binding.button.setOnClickListener {
            val messageText = binding.input.text.toString()
            if (messageText.isNotEmpty()){
                homeViewModel.insertMessage(Message(messageText))
                Toast.makeText(requireContext(), "Value Inserted", Toast.LENGTH_LONG).show()
                binding.input.text.clear()
            }else Toast.makeText(requireContext(), "Please Enter Something", Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(message: Message) {
        homeViewModel.deleteMessage(message)
        Toast.makeText(requireContext(), "Value Deleted", Toast.LENGTH_LONG).show()

    }
}
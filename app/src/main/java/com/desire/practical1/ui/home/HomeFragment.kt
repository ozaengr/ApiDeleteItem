package com.desire.practical1.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.desire.practical1.api.User
import com.desire.practical1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickAllProducts()
        onClickAddProducts()
    }
    private fun onClickAllProducts() {
        binding.allProducts.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllProductFragment())
        }
    }

    private fun onClickAddProducts() {
        binding.addProduct.setOnClickListener {
            val intent = Intent(requireContext(), AddProductActivity::class.java)
            startActivity(intent)
        }
    }
}
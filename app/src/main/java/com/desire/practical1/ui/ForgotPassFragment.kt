package com.desire.practical1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.desire.practical1.R
import com.desire.practical1.databinding.FragmentForgotPassBinding

class ForgotPassFragment : Fragment() {
    private lateinit var binding: FragmentForgotPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPassBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickForgotButtun()

    }

    private fun onClickForgotButtun() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(ForgotPassFragmentDirections.actionForgotPassFragmentToLoginFragment())
        }
    }


}
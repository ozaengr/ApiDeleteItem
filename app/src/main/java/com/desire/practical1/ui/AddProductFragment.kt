package com.desire.practical1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.desire.practical1.R
import com.desire.practical1.api.AddRequest
import com.desire.practical1.api.AddResponse
import com.desire.practical1.api.Retrofit
import com.desire.practical1.databinding.FragmentAddProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductFragment : Fragment() {
    private lateinit var binding: FragmentAddProductBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.addProduct.setOnClickListener {
            if (binding.edittextName.text.toString()
                    .isEmpty() || binding.edittextCategory.text.toString()
                    .isEmpty() || binding.image.display.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "All Fields are mendatory", Toast.LENGTH_SHORT)
                    .show()
            } else {

                var model = AddRequest(
                    binding.edittextName.text.toString(),
                    binding.edittextCategory.text.toString(),
                    "https://i.pravatar.cc",
                )
                callApiToAddItem(model)

            }
        }
    }

    private fun callApiToAddItem(model: AddRequest) {
        var call: Call<AddResponse> = Retrofit.api.addItem(model)
        call.enqueue(object : Callback<AddResponse> {

            override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                if (response.isSuccessful) {
                   Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"UnSuccessful",Toast.LENGTH_SHORT).show()
            }

        })
    }

}
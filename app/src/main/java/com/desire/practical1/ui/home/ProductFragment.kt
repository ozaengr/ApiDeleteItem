package com.desire.practical1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.desire.practical1.api.Retrofit
import com.desire.practical1.api.User
import com.desire.practical1.databinding.FragmentProductBinding
import com.desire.practical1.homeadapter.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private val args: ProductFragmentArgs by navArgs()
    private lateinit var productAdapter: HomeAdapter
    var listOfData = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = HomeAdapter(listOfData)
        initView()
        ivBack()
        onDeleted()
    }

    private fun ivBack() {
        binding.ivCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        binding.tvTitle.text = args.title.title
        binding.tvCategory.text = args.title.category
        Glide.with(binding.imgRcv).load(args.title.image).into(binding.imgRcv)
        Log.d("test", "onViewCreated: ${args.title.title}")

    }

    private fun onDeleted() {
        binding.btnDelete.setOnClickListener {
                productAdapter.onItemClick = {item ->
                    Log.i("test", "${item.id}")
                    onClickDeleteButton(item.id)
                    Log.i("test", "Deleted Item : ${item.id}")
                    var index = productAdapter.listOfData.indexOfFirst { it.id == item.id }
                    productAdapter.deleteItem(index)
                    findNavController().navigateUp()
                }
        }
    }

    private fun onClickDeleteButton(id: Int) {
        var call: Call<Unit> = Retrofit.api.deleteData(id)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(requireContext(),"Un-Success",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(requireContext(),"Fail",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
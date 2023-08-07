package com.desire.practical1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.desire.practical1.api.User
import com.desire.practical1.databinding.FragmentAllProductBinding
import com.desire.practical1.homeadapter.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductFragment : Fragment() {

    private lateinit var binding: FragmentAllProductBinding
    private lateinit var adapter: HomeAdapter

    var listOfData = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        ivBack()
        onRefresh()


    }

    private fun ivBack() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        var call: Call<ArrayList<User>> = com.desire.practical1.api.Retrofit.api.getData()
        binding.progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.progressBar.visibility = View.INVISIBLE
                        listOfData.addAll(it)
                        adapter = HomeAdapter(listOfData)
                        binding.rcvAllProduct.adapter = adapter
                        binding.rcvAllProduct.layoutManager =
                            GridLayoutManager(requireContext(), 2)
                        binding.swiperefresh.isRefreshing = false
                        adapter.onItemClick = { item ->
                            Log.i("test", "${item.id}")
                            initViewDelete(item.id)

                            val index = adapter.listOfData.indexOfFirst { it.id == item.id }
                            adapter.deleteItem(index)
                        }
                        adapter.onClickItem = {
                            findNavController().navigate(
                                AllProductFragmentDirections.actionAllProductFragmentToProductFragment(
                                    it
                                )
                            )
                        }
                    }
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.swiperefresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }
    private fun initViewDelete(id: Int) {
        var call: Call<Unit> = com.desire.practical1.api.Retrofit.api.deleteData(id)
        call.enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to Deleted", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed to Deleted", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun onRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = false
        }
    }


}
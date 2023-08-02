package com.desire.practical1.home

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.desire.practical1.R
import com.desire.practical1.api.User
import com.desire.practical1.databinding.ActivityHomeBinding
import com.desire.practical1.ui.AddProductFragment
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickAllProducts()
        onClickAddProducts()

    }

    private fun onClickAllProducts() {
        binding.allProducts.setOnClickListener {
            var intent = Intent(this,AllProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickAddProducts() {
        binding.addProduct.setOnClickListener {
            var intent = Intent(this,AddProductActivity::class.java)
            startActivity(intent)
        }
    }
}
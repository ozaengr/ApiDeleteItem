package com.desire.practical1.ui

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.desire.practical1.home.HomeActivity
import com.desire.practical1.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickForgotButtun()
        onClickSignUp()
        onClickSignIn()
        initView()
    }

    private fun initView() {
        binding.btnGoogleSignin.setOnClickListener {
            if (binding.textInputEmail.text.toString()
                    .isEmpty() || binding.textInputPass.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "All Fields are mendatory", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isValidEmail(binding.textInputEmail.text.toString())) {
                Toast.makeText(requireContext(), "Enter valid Email", Toast.LENGTH_SHORT).show()
            } else if (!isValidPass(binding.textInputPass.text.toString())) {
                Toast.makeText(requireContext(), "Enter valid password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun isValidPass(pass: String): Boolean {
        var passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{4,}\$"
        var passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(pass) != null
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()

    }

    private fun onClickForgotButtun() {
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPassFragment())
        }
    }

    private fun onClickSignIn() {
        binding.tvSignin.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignInFragment())
        }
    }

    private fun onClickSignUp() {
        var title = "Don't have an account? Sign up"
        var spannableString = SpannableString(title)
        var clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                binding.tvSignup.setOnClickListener {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSidnUpFragment())
                }
            }
        }
        spannableString.setSpan(clickableSpan, 23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvSignup.text = spannableString
        binding.tvSignup.setText(spannableString, TextView.BufferType.SPANNABLE)
        binding.tvSignup.movementMethod = LinkMovementMethod.getInstance()
    }
}
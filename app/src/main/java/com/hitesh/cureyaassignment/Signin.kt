package com.hitesh.cureyaassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Signin : Fragment() {

    lateinit var signup: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_signin, container, false)

        signup = v.findViewById(R.id.tv_signup)

        signup.setOnClickListener {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Signup()).addToBackStack(null).commit()
        }

        return v
    }
}
package com.hitesh.cureyaassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.hitesh.cureyaassignment.adapter.UserAdapter
import com.squareup.picasso.Picasso

class Profile : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var age: TextView
    lateinit var bio: TextView
    lateinit var displaypicture: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        name = v.findViewById(R.id.profile_user_name)
        email = v.findViewById(R.id.profile_user_email)
        age = v.findViewById(R.id.profile_user_age)
        bio = v.findViewById(R.id.profile_user_bio)
        displaypicture = v.findViewById(R.id.profile_user_icon)

        val currentUser = FirebaseAuth.getInstance().currentUser?.uid

        var bundle = getArguments()
        if (bundle?.getString("name") == null) {
            db.collection("Users").document(currentUser!!).get().addOnSuccessListener {
                Log.d("working", it.toString())
                setUserDetails(
                    it.get("name").toString(),
                    it.get("email").toString(),
                    it.get("age").toString(),
                    it.get("bio").toString(),
                    it.get("imgurl").toString()
                )
            }
        } else {
            setUserDetails(
                bundle?.getString("name")!!,
                bundle?.getString("email")!!,
                bundle?.getString("age")!!,
                bundle?.getString("bio")!!,
                bundle?.getString("imgurl")!!
            )
        }


        return v
    }

    private fun setUserDetails(n: String, e: String, a: String, b: String, i: String) {
        name.text = n
        email.text = e
        age.text = a
        bio.text = b
        Picasso.get().load(i).into(displaypicture)
    }

}
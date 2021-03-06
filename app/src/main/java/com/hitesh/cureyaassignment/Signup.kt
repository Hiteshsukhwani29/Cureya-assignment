package com.hitesh.cureyaassignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class Signup : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var age: EditText
    lateinit var bio: EditText
    lateinit var password: EditText
    lateinit var BtnSignup: Button

    var data: HashMap<String, String> = HashMap<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_signup, container, false)
        auth = FirebaseAuth.getInstance()

        name = v.findViewById(R.id.et_signup_name)
        email = v.findViewById(R.id.et_signup_email)
        age = v.findViewById(R.id.et_signup_age)
        bio = v.findViewById(R.id.et_signup_bio)
        password = v.findViewById(R.id.et_signup_password)

        BtnSignup = v.findViewById(R.id.btn_signup)

        BtnSignup.setOnClickListener {
            signup()
        }

        return v
    }

    private fun signup() {
        if (name.text.isEmpty()) {
            name.setError("Name is Required")
            name.requestFocus()
        }
        if (email.text.isEmpty()) {
            email.setError("Email is Required")
            email.requestFocus()
        }
        if (age.text.isEmpty()) {
            age.setError("Age is Required")
            age.requestFocus()
        }
        if (bio.text.isEmpty()) {
            bio.setError("Bio is Required")
            bio.requestFocus()
        }
        if (password.text.length < 6) {
            password.setError("Password should not be less than 6 characters")
            password.requestFocus()
        }

        if (!name.text.isEmpty() && !email.text.isEmpty() && !age.text.isEmpty() && !bio.text.isEmpty() && password.text.length >= 6) {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful()) {
                        data.put("name",name.text.toString())
                        data.put("email",email.text.toString())
                        data.put("age",age.text.toString())
                        data.put("bio",bio.text.toString())
                        val n = name.text.split(" ")
                        data.put("imgurl","https://ui-avatars.com/api/?name=${n[0]}+${n[1]}")
                        addDataToFirestore()
                    } else {
                        Toast.makeText(
                            activity,
                            "Failed to signup! Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun addDataToFirestore() {
        db = FirebaseFirestore.getInstance()
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        val dbCourses: DocumentReference = db.collection("Users").document(currentuser)
        dbCourses.set(data)
            .addOnSuccessListener(OnSuccessListener<Void?> {
                val intent = Intent(activity, MainActivity2::class.java)
                startActivity(intent)
                requireActivity().finish()
            })
            .addOnFailureListener(OnFailureListener { })
    }

}
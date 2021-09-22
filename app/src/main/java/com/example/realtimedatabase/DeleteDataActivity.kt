package com.example.realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabase.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener {
            var userName = binding.etusername.text.toString()
            if (userName.isNotEmpty())
                deleteData(userName)
            else

                Toast.makeText(this, "Please enter the username", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).removeValue().addOnSuccessListener {
            binding.etusername.text.clear()
            Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}
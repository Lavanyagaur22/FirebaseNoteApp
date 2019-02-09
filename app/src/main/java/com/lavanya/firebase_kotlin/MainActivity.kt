package com.lavanya.firebase_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val rootRef by lazy {
        FirebaseDatabase.getInstance().reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAdd.setOnClickListener {
            var message = etMessage.text.toString()
            var time = System.currentTimeMillis()

            var n = Note().apply {
                message = message
                time = time
            }

            rootRef.push().setValue(n)
                .addOnSuccessListener {
                    Log.e("TAG", "success")
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }


        rootRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                val note : Note? =dataSnapshot.getValue(Note::class.java)

                Log.e("TAG","onChildChanged ${note?.message}")
                Log.e("TAG","onChildChanged  key : $p1")


            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

        })


    }
}

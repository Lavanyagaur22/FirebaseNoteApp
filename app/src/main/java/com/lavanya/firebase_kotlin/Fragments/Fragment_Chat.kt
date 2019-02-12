package com.lavanya.firebase_kotlin.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.lavanya.firebase_kotlin.Adapters.CustomAdapter
import com.lavanya.firebase_kotlin.Models.Note
import com.lavanya.firebase_kotlin.R
import kotlinx.android.synthetic.main.fragment_chat.view.*

class Fragment_Chat : Fragment() {

    private val rootRef by lazy {
        FirebaseDatabase.getInstance().reference
    }
    private val TAG = javaClass.simpleName
    val notesArrayList = arrayListOf<Note>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = CustomAdapter(notesArrayList)


     with(view){
         recyclerView.layoutManager=LinearLayoutManager(activity)
         recyclerView.adapter=adapter


        view.buttonAdd.setOnClickListener {

             val message = view.etMessage.text.toString()

             val n = Note().apply {
                 this.message = message
             }

             Log.e(TAG, "value just after adding a message  ${n.message}")

             rootRef.push().setValue(n)
                 .addOnSuccessListener {
                     Log.e("TAG", "success")
                 }
                 .addOnFailureListener {
                     it.printStackTrace()
                 }
                 .addOnCompleteListener {
                     Log.e(TAG, "  OnCompleteListener after pushing  ")
                 }

             notesArrayList.add(n)
             Log.e(TAG, " Size of arraylis is : ${notesArrayList.size}")
             adapter.notifyDataSetChanged()

         } // end of add button


         rootRef.addChildEventListener(object : ChildEventListener {

             override fun onCancelled(p0: DatabaseError) {

             }

             override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
             }

             override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {

                 val note: Note? = dataSnapshot.getValue(Note::class.java)

                 Log.e("TAG", "onChildChanged ${note?.message}")
//                Log.e("TAG", "onChildChanged  key : $p1")


             }

             override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

                 val note: Note? = dataSnapshot.getValue(Note::class.java)

                 Log.e("TAG", "onChildAdded ${note?.message}")
//                Log.e("TAG", "onChildAdded  key : $p1")

                 note?.let {
                     notesArrayList.add(it)
                 }

             }

             override fun onChildRemoved(dataSnapshot: DataSnapshot) {
             }

         })


         rootRef.addValueEventListener(object : ValueEventListener {
             override fun onCancelled(databaseError: DatabaseError) {
                 Log.e(TAG, "database error is ${databaseError.message}")

             }

             override fun onDataChange(dataSnapshot: DataSnapshot) {

                 val children = dataSnapshot.children

                 for (ds in children) {

                     val note = ds.getValue(Note::class.java)
                     Log.e("TAG", "ValueEventListener----- ${note?.message} ")
                 }


             }

         })
     }




    }



}
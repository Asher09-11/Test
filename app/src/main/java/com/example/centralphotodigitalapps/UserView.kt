package com.example.centralphotodigitalapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserView : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatList: ArrayList<Message>
    private lateinit var chatAdapter: MessageAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference
        chatList = ArrayList()
        chatAdapter = MessageAdapter(this, chatList)

        chatRecyclerView = findViewById(R.id.userRecyclerView)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        val targetUid = "dSoRswUqaZepxOq3PmhhCsfoyr03"

        val chatPath = "chats/${mAuth.currentUser?.uid + targetUid}/messages"

        mDbRef.child(chatPath).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                chatList.clear()
                for (postSnapshot in snapshot.children) {

                    val currentMessage = postSnapshot.getValue(Message::class.java)
                    chatList.add(currentMessage!!)
                }
                chatAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserView, "Error fetching data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

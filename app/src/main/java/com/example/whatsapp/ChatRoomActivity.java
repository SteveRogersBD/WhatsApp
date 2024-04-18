package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.whatsapp.Adapters.ChatAdapter;
import com.example.whatsapp.Models.MessageModel;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.example.whatsapp.databinding.ActivityChatRoomBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatRoomActivity extends AppCompatActivity {

    ActivityChatRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final String senderId = FirebaseAuth.getInstance().getUid();

        binding.userNameChatDetails.setText("Friends Group");

        final ChatAdapter adapter = new ChatAdapter(messageModels, this);
        binding.chatDetailsRecycler.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        binding.chatDetailsRecycler.setLayoutManager(lm);

        database.getReference().child("Group Chat")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                messageModels.clear();
                                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                                {
                                    MessageModel model = dataSnapshot.getValue(MessageModel.class);
                                    messageModels.add(model);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = binding.sendMessageEt.getText().toString();
                final MessageModel model = new MessageModel(senderId,message);
                model.setTimeStamp(new Date().getTime());
                binding.sendMessageEt.setText("");
                database.getReference().child("Group Chat").push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
            }
        });


    }
}
package com.example.whatsapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Models.MessageModel;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> list;
    Context context;
    String recId;

    public ChatAdapter(ArrayList<MessageModel> list, Context context, String recId) {
        this.list = list;
        this.context = context;
        this.recId = recId;
    }

    public ChatAdapter(ArrayList<MessageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SennderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;
    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
            return  SENDER_VIEW_TYPE;
        else return RECEIVER_VIEW_TYPE;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel= list.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete the message?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String sender = FirebaseAuth.getInstance().getUid()+recId;
                                database.getReference().child("chats").child(sender)
                                        .child(messageModel.getMessageId())
                                        .setValue(null);

                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return false;
            }
        });

        if(holder.getClass()==SennderViewHolder.class){
            ((SennderViewHolder)holder).sMessage.setText(messageModel.getMessage());
        }
        else {
            ((RecieverViewHolder)holder).rMessage.setText(messageModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView rMessage, rTime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            rMessage = itemView.findViewById(R.id.receiver_text_tv);
            rTime = itemView.findViewById(R.id.receiver_time_tv);
        }
    }
    public class SennderViewHolder extends RecyclerView.ViewHolder {
        TextView sMessage, sTime;
        public SennderViewHolder(@NonNull View itemView) {
            super(itemView);
            sMessage = itemView.findViewById(R.id.senderText);
            sTime = itemView.findViewById(R.id.senderTime);
        }
    }
}

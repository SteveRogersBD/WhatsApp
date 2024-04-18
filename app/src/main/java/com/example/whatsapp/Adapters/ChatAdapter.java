package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Models.MessageModel;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> list;
    Context context;

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

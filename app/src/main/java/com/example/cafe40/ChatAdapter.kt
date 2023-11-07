package com.example.cafe40

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private var chatList = emptyList<ChatMessage>()

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val currentItem = chatList[position]
        holder.messageTextView.text = currentItem.message
        holder.senderTextView.text = currentItem.sender
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun submitList(list: List<ChatMessage>) {
        chatList = list
        notifyDataSetChanged()
    }
}

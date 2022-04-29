package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemMessageBinding
import com.example.myapplication.roomDatabase.Message

class MessageAdapter(private val listener: IMessageRVAdapter): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val allMessages = ArrayList<Message>()

    inner class MessageViewHolder(val binding: ItemMessageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = MessageViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        view.binding.deleteButton.setOnClickListener {
            listener.onItemClicked(allMessages[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = allMessages[position]
        holder.binding.messageText.text = currentMessage.message
    }

    override fun getItemCount(): Int {
        return allMessages.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Message>){
        allMessages.clear()
        allMessages.addAll(newList)
        notifyDataSetChanged()
    }
}

interface IMessageRVAdapter{
    fun onItemClicked(message: Message)
}
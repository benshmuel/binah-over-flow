package com.example.binahoverflow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.binahoverflow.data.BinahAdapterItem
import com.example.binahoverflow.data.QuestionAdapterItem
import com.example.binahoverflow.databinding.AdapterQuestionItemViewBinding
import com.example.binahoverflow.ui.fragment.BinahListFragment

class BinahAdapter(val onAdapterItemClicked: BinahListFragment.OnAdapterItemClicked) :
    ListAdapter<BinahAdapterItem, RecyclerView.ViewHolder>(AdapterDiffCallBack()) {


    interface OnItemClicked {
        fun goToWebView(withUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return QuestionViewHolder(
            AdapterQuestionItemViewBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is QuestionViewHolder -> holder.bind(getItem(position) as QuestionAdapterItem)
        }
    }


    inner class QuestionViewHolder(private val binding: AdapterQuestionItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(questionAdapterItem: QuestionAdapterItem) {

            //Using DataBinding in order to set the obj into XML.
            binding.question = questionAdapterItem

            //Loading profile image fro the specific user.
            Glide.with(itemView.context).load(questionAdapterItem.getItemOwnerImgUrl())
                .into(binding.questionOwnerProfileUrlImg)

            //Setting the onClick which will eventually lead to WebView.
            binding.root.setOnClickListener {
                onAdapterItemClicked.goToWebView(questionAdapterItem.getQuestionUrl())
            }
        }
    }
}

/**
 * DiffUtil in order to increase performance while filtering etc.
 *
 * */
class AdapterDiffCallBack : DiffUtil.ItemCallback<BinahAdapterItem>() {
    override fun areItemsTheSame(oldItem: BinahAdapterItem, newItem: BinahAdapterItem): Boolean {
        return oldItem.getItemIdentifier() == newItem.getItemIdentifier()
    }

    override fun areContentsTheSame(oldItem: BinahAdapterItem, newItem: BinahAdapterItem): Boolean {
        return oldItem.getItemTitle() == newItem.getItemTitle()
    }
}
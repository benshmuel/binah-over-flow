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

class binahAdapter(val onAdapterItemClicked: BinahListFragment.OnAdapterItemClicked) :
    ListAdapter<BinahAdapterItem, RecyclerView.ViewHolder>(BinahAdapterDiffCallBack()) {

    companion object BinahAdapterViewType {

        const val VIEW_TYPE_QUESTION = 111
        const val VIEW_TYPE_ANSWER = 222
        const val NO_RESULTS = 999
    }


    interface OnItemClicked {
        fun goToWebView(withUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_QUESTION -> {
                QuestionViewHolder(
                    AdapterQuestionItemViewBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
/*            VIEW_TYPE_ANSWER -> {
            }
            NO_RESULTS -> {
            }*/
            else -> throw Exception("The viewTpe : $viewType  is not valid")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is QuestionViewHolder -> holder.bind(getItem(position) as QuestionAdapterItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is QuestionAdapterItem -> VIEW_TYPE_QUESTION
            else -> -1
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


            binding.root.setOnClickListener {
                onAdapterItemClicked.goToWebView(questionAdapterItem.getQuestionUrl())
            }
        }
    }

/*    inner class AnswerViewHolder(private val binding: AdapterQuestionItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    inner class NoResultViewHolder(private val binding: AdapterQuestionItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }*/

}


/**
 *
 * */
class BinahAdapterDiffCallBack : DiffUtil.ItemCallback<BinahAdapterItem>() {
    override fun areItemsTheSame(oldItem: BinahAdapterItem, newItem: BinahAdapterItem): Boolean {

        return oldItem.getItemIdentifier() == newItem.getItemIdentifier()
    }

    override fun areContentsTheSame(oldItem: BinahAdapterItem, newItem: BinahAdapterItem): Boolean {
        return oldItem.getItemTitle() == newItem.getItemTitle()
    }


}

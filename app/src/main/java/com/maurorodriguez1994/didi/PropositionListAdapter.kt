package com.maurorodriguez1994.didi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maurorodriguez1994.didi.room.entity.Proposition

class PropositionListAdapter(val event: (Proposition) -> Unit) : ListAdapter<Proposition, PropositionViewHolder>(PropositionComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropositionViewHolder {
        return PropositionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PropositionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bindText(current.questionId, current.wordId)
        holder.bindTag(current)
        holder.bindColor(current.state)
        holder.bindOnClickEvent {
            event(it)
        }
    }
}

class PropositionComparator : DiffUtil.ItemCallback<Proposition>() {
    override fun areItemsTheSame(oldItem: Proposition, newItem: Proposition): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Proposition, newItem: Proposition): Boolean =
        oldItem == newItem
}

class PropositionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val propositionItemView: TextView = itemView.findViewById(R.id.textView)

    fun bindText(question: String, word: String) {
        propositionItemView.text = itemView.context.getString(R.string.proposition, question, word)
    }

    fun bindTag(proposition: Proposition) {
        propositionItemView.tag = proposition
    }

    fun bindColor(state: Boolean) {
        if (state) {
            propositionItemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.state_true))
        } else {
            propositionItemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.state_false))
        }
    }

    fun bindOnClickEvent(event: (Proposition) -> Unit) {
        propositionItemView.setOnClickListener {
            val proposition = propositionItemView.tag as Proposition
            proposition.state = !proposition.state
            bindColor(proposition.state)
            event(proposition)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PropositionViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return PropositionViewHolder(view)
        }
    }
}
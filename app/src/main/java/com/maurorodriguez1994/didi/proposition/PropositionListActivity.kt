package com.maurorodriguez1994.didi.proposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maurorodriguez1994.didi.DidIApplication
import com.maurorodriguez1994.didi.R

class PropositionListActivity : AppCompatActivity() {
    private val propositionViewModel: PropositionViewModel by viewModels {
        PropositionViewModelFactory((application as DidIApplication).propositionRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposition_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PropositionListAdapter {
            propositionViewModel.insert(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        propositionViewModel.allPropositions.observe(this) { propositions ->
            // Update the cached copy of the words in the adapter.
            propositions.let { adapter.submitList(it) }
        }
    }
}
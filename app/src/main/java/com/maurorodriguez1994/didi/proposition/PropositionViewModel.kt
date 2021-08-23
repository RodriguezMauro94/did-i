package com.maurorodriguez1994.didi.proposition

import androidx.lifecycle.*
import com.maurorodriguez1994.didi.repository.PropositionRepository
import com.maurorodriguez1994.didi.room.entity.Proposition
import kotlinx.coroutines.launch

class PropositionViewModel(private val repository: PropositionRepository) : ViewModel() {
    val allPropositions: LiveData<List<Proposition>> = repository.propositions.asLiveData()

    fun insert(proposition: Proposition) = viewModelScope.launch {
        repository.insert(proposition)
    }
}

class PropositionViewModelFactory(private val repository: PropositionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropositionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PropositionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
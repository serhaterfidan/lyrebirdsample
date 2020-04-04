package com.studio.lyrebirdsample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studio.lyrebirdsample.model.Candidates

class MainViewModel : ViewModel() {

    val candidatesResponse: MutableLiveData<ArrayList<Candidates>?> by lazy {
        MutableLiveData<ArrayList<Candidates>?>()
    }

}
package com.studio.lyrebirdsample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.studio.lyrebirdsample.R
import com.studio.lyrebirdsample.adapter.CandidatesRecyclerViewAdapter
import com.studio.lyrebirdsample.model.Candidates
import com.studio.lyrebirdsample.repository.MainRepository
import com.studio.lyrebirdsample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var candidates: ArrayList<Candidates> = ArrayList()
    lateinit var candidatesRecyclerViewAdapter: CandidatesRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        mainRepository = MainRepository(
            this,
            mainViewModel
        )

        val socketCartObserver = Observer<ArrayList<Candidates>?> { response ->

            response?.let {

                candidates = it

                //Update the orders adapter with filled list
                candidatesRecyclerViewAdapter =
                    CandidatesRecyclerViewAdapter(
                        this,
                        candidates
                    )
                candidatesRecyclerView.adapter = candidatesRecyclerViewAdapter

            }

        }

        mainViewModel.candidatesResponse.observe(this, socketCartObserver)
        mainRepository.getCandidates()

        candidatesRecyclerViewAdapter =
            CandidatesRecyclerViewAdapter(
                this,
                candidates
            )
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        candidatesRecyclerView.layoutManager = layoutManager
        candidatesRecyclerView.adapter = candidatesRecyclerViewAdapter

        Glide.with(this)
            .load(R.drawable.download)
            .into(backImage)

        removeView.setOnClickListener {
            frontImage.setImageDrawable(null)
        }
    }

    fun onHorizontalSectionsItemClick(position : Int){
        Glide.with(this)
            .load(candidates[position].overlayUrl)
            .into(frontImage)

    }

}

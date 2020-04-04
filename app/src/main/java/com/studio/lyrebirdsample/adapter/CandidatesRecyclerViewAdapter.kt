package com.studio.lyrebirdsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.lyrebirdsample.model.Candidates
import com.studio.lyrebirdsample.activity.MainActivity
import com.studio.lyrebirdsample.R
import kotlinx.android.synthetic.main.item_candidate.view.*
import java.util.*

class CandidatesRecyclerViewAdapter(
    private var context: Context,
    private var candidates: ArrayList<Candidates>
) : RecyclerView.Adapter<CandidatesRecyclerViewAdapter.CandidateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        //inflate the layout file
        val candidateView = LayoutInflater.from(parent.context).inflate(R.layout.item_candidate, parent, false)

        return CandidateViewHolder(candidateView)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {

        val candidate = candidates[position]

        Glide.with(context)
            .load(candidate.overlayUrl)
            .into(holder.candidateImage)

        holder.candidateText.text = candidate.overlayName

        holder.itemView.setOnClickListener {
            (context as MainActivity).onHorizontalSectionsItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return candidates.size
    }

    inner class CandidateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var candidateImage: ImageView = view.candidatesImage
        internal var candidateText: TextView = view.candidateText
    }
}
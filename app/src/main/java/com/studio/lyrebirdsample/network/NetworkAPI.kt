package com.studio.lyrebirdsample.network

import com.studio.lyrebirdsample.model.Candidates
import io.reactivex.Observable
import retrofit2.http.*

interface NetworkAPI {

    @GET("overlay.json")
    fun getCandidates(): Observable<ArrayList<Candidates>>

}
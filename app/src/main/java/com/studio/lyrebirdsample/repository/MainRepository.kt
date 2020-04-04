package com.studio.lyrebirdsample.repository

import android.content.Context
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.studio.lyrebirdsample.viewmodel.MainViewModel
import com.studio.lyrebirdsample.network.NetworkAPI
import com.studio.lyrebirdsample.network.NetworkClient
import com.studio.lyrebirdsample.network.NetworkCompanions.Companion.baseUrl
import com.studio.lyrebirdsample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository(
    private var context: Context,
    private var viewModel: MainViewModel
) {

    private var mainViewModel: MainViewModel = viewModel
    private var progressDialog: SweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)

    init {

        progressDialog.progressHelper.barColor = ContextCompat.getColor(context,
            R.color.colorAccent
        )
        progressDialog.titleText = "Loading"
        progressDialog.progressHelper.circleRadius = 100
        progressDialog.setCancelable(false)

    }

    fun getCandidates() {

        progressDialog.show()

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getCandidates()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    progressDialog.dismiss()
                    mainViewModel.candidatesResponse.value = response


                },
                {

                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

}
package com.kserno.foodie.addfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import java.io.File

/**
 *  Created by filipsollar on 2019-04-10
 */
class AddFoodViewModel(private val api: Api): ViewModel() {

    val actionPickPhoto = MutableLiveData<Action<Void>>()
    val actionDone = MutableLiveData<Action<Void>>()

    val uploading = MutableLiveData<Boolean>()

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val photo = MutableLiveData<File>()

    fun doneClicked() {
        // TODO check
        uploading.postValue(true)
        api.addFood(
                name.value ?: "",
                description.value?: "",
                price.value?.toDouble() ?: 0.0,
                photo.value!!)
                .applySchedulers()
                .subscribe({
                    actionDone.postValue(Action())
                }, {
                    it.printStackTrace()
                })

    }

    fun pickPhotoClicked() {
        actionPickPhoto.postValue(Action())
    }


}
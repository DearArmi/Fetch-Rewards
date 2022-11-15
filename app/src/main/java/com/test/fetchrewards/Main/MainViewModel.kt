package com.test.fetchrewards.Main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.fetchrewards.Api.ApiResponseStatus
import com.test.fetchrewards.DataBase.getDataBase
import com.test.fetchrewards.Item
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Getting Database
    val dataBase = getDataBase(application)
    val mainRepository = MainRepository(dataBase)

    private var _status = MutableLiveData<ApiResponseStatus>()
    val status: MutableLiveData<ApiResponseStatus>
        get() = _status

    private var _itemList = MutableLiveData<MutableList<Item>>()
    val itemList: LiveData<MutableList<Item>>
        get() = _itemList

    init {
        loadItems()
    }

    private fun loadItems() {

        viewModelScope.launch {
            try{
                _status.value = ApiResponseStatus.LOADING

                //Verifying rows in DB
                if(mainRepository.checkDataBase() > 0){
                    _itemList.value = mainRepository.fetchItemsFromDB()
                }else{
                    _itemList.value = mainRepository.fetchItems()
                }
                _status.value = ApiResponseStatus.DONE

            }catch (e: UnknownHostException){
                _status.value = ApiResponseStatus.ERROR
                Log.d("","No internet", e)

            }
        }


    }
}
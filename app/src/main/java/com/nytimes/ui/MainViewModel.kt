package com.nytimes.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.data.repositories.UserRepository
import com.nytimes.data.response.GetMasters
import com.nytimes.support.ApiException
import com.nytimes.support.Coroutines
import com.nytimes.support.NoInternetException

class MainViewModel (
    private val repository: UserRepository
) : ViewModel() {

    private val _exception = MutableLiveData<Int>()
    private val _error = MutableLiveData<String>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getError(): LiveData<String> {
        return _error
    }

    fun getException(): LiveData<Int> {
        return _exception
    }

    var _getMaster = MutableLiveData<GetMasters>()

    fun getMaster(): LiveData<GetMasters> {
        return _getMaster
    }


    fun GetMastere()
    {

        Coroutines.io {
            try {


              val result = repository.MasterMostpopular()
                _getMaster.postValue(result)

                //  GetWoundAssessmentMaster = result.data

                _isLoading.postValue(false)
            } catch (e: ApiException) {
                _error.postValue(e.message)
                _isLoading.postValue(false)
            } catch (e: NoInternetException) {
                _error.postValue(e.message)
                _isLoading.postValue(false)
            }
        }

    }




}
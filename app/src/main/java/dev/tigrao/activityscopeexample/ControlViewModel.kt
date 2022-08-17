package dev.tigrao.activityscopeexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControlViewModel : ViewModel() {

    private var total: Int = 0

    private val _liveData = MutableLiveData<Int>()
    val liveData: LiveData<Int> = _liveData

    fun increment(by: Int) {
        if (total < 100) {
            total += by
            _liveData.postValue(total)
        }
    }

    fun decrement(by: Int) {
        if (total > 0) {
            total -= by
            _liveData.postValue(total)
        }
    }

    fun setTotal(by: Int) {
        if (by in 0..100) {
            total = by

            _liveData.postValue(total)
        }
    }
}
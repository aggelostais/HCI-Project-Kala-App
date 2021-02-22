package ntua.hci.kalaapp.ui.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ntua.hci.kalaapp.R

class SocialViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Social Fragment"
    }
    val text: LiveData<String> = _text
}
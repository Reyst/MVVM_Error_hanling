package gsihome.reyst.mvvm.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gsihome.reyst.mvvm.example.domain.data.Album
import gsihome.reyst.mvvm.example.domain.data.User

class MainViewModel : ViewModel() {

    private val _selectedUser = MutableLiveData<User?>()
    val selectedUser: LiveData<User?> = _selectedUser

    private val _selectedAlbum = MutableLiveData<Album?>()
    val selectedAlbum: LiveData<Album?> = _selectedAlbum

    fun selectUser(user: User?) {
        _selectedUser.postValue(user)

        selectAlbum(null)
    }

    fun selectAlbum(album: Album?) {
        _selectedAlbum.postValue(album)
    }

}
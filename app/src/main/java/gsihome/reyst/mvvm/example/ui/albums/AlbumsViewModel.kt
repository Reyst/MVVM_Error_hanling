package gsihome.reyst.mvvm.example.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gsihome.reyst.mvvm.example.domain.data.User
import gsihome.reyst.mvvm.example.domain.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val repository: AlbumRepository
): ViewModel() {

    val albums = repository.albums

    fun loadAlbums(user: User?) {

        viewModelScope.launch {
            repository.loadAlbums(user)
        }

    }

}
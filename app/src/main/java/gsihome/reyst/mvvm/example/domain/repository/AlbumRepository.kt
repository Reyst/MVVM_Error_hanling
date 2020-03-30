package gsihome.reyst.mvvm.example.domain.repository

import androidx.lifecycle.LiveData
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.Album
import gsihome.reyst.mvvm.example.domain.data.User

interface AlbumRepository {
    val albums: LiveData<Resource<List<Album>>>
    suspend fun loadAlbums(user: User?)
}
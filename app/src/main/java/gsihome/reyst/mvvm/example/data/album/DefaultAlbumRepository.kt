package gsihome.reyst.mvvm.example.data.album

import androidx.lifecycle.MutableLiveData
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.Album
import gsihome.reyst.mvvm.example.domain.data.User
import gsihome.reyst.mvvm.example.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultAlbumRepository(
    private val dataSource: RemoteAlbumDataSource
) : AlbumRepository {

    override val albums: MutableLiveData<Resource<List<Album>>> = MutableLiveData()

    override suspend fun loadAlbums(user: User?) {
        withContext(Dispatchers.IO) {
            albums.postValue(Resource.Loading())
            try {
                user?.let { dataSource.getAlbums(it.id) }
                    ?.toDomain()
                    ?.also { albums.postValue(Resource.Success(it)) }
                    ?: throw Exception("User is not selected!")
            } catch (e: Exception) {
                albums.postValue(Resource.Error(e))
            }
        }
    }
}
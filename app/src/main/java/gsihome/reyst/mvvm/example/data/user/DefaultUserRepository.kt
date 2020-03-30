package gsihome.reyst.mvvm.example.data.user

import androidx.lifecycle.MutableLiveData
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.User
import gsihome.reyst.mvvm.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultUserRepository(
    private val dataSource: RemoteUserDataSource
): UserRepository {

    override val users: MutableLiveData<Resource<List<User>>> = MutableLiveData()

    override suspend fun loadUsers() {
        withContext(Dispatchers.IO) {
            users.postValue(Resource.Loading())
            try {
                dataSource.getUsers()
                    .toDomain()
                    .also { users.postValue(Resource.Success(it)) }
            } catch (e: Exception) {
                users.postValue(Resource.Error(e))
            }
        }
    }

}
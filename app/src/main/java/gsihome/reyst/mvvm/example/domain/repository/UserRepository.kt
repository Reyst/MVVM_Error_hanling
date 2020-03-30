package gsihome.reyst.mvvm.example.domain.repository

import androidx.lifecycle.LiveData
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.User

interface UserRepository {

    val users: LiveData<Resource<List<User>>>

    suspend fun loadUsers()

}
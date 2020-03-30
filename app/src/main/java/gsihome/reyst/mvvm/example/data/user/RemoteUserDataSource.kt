package gsihome.reyst.mvvm.example.data.user

import androidx.annotation.WorkerThread

interface RemoteUserDataSource {

    @WorkerThread
    fun getUsers() : List<UserDto>
}
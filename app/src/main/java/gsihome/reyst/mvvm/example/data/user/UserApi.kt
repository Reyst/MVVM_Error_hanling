package gsihome.reyst.mvvm.example.data.user

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("/users")
    fun getUsers() : Call<List<UserDto>>

}
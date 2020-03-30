package gsihome.reyst.mvvm.example.data.user

class RetrofitUserDataSource(
    private val api: UserApi
) : RemoteUserDataSource {
    override fun getUsers(): List<UserDto> {
        return api.getUsers().execute().body() ?: emptyList()
    }
}
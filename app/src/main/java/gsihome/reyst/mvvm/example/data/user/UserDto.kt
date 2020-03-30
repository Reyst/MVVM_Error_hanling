package gsihome.reyst.mvvm.example.data.user

import gsihome.reyst.mvvm.example.domain.data.User

data class UserDto(
    val id: Int,
    val name: String,
    val username: String
)

fun UserDto.toDomain() = User(id, name, username)
fun Collection<UserDto>.toDomain() = map { it.toDomain() }


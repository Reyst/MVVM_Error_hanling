package gsihome.reyst.mvvm.example.data.album

import gsihome.reyst.mvvm.example.domain.data.Album

data class AlbumDto(
    val id: Int,
    val title: String
)

fun AlbumDto.toDomain() = Album(id, title)
fun Collection<AlbumDto>.toDomain() = map { it.toDomain() }


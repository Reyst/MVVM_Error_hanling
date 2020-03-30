package gsihome.reyst.mvvm.example.data.album

interface RemoteAlbumDataSource {
    fun getAlbums(userId: Int): List<AlbumDto>
}

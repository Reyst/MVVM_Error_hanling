package gsihome.reyst.mvvm.example.data.album

class RetrofitAlbumDataSource(
    private val api: AlbumApi
) : RemoteAlbumDataSource {
    override fun getAlbums(userId: Int): List<AlbumDto> {
        return api.getAlbums(userId).execute().body() ?: emptyList()
    }
}
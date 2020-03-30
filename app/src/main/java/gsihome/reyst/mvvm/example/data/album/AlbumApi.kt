package gsihome.reyst.mvvm.example.data.album

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {

    @GET("/albums")
    fun getAlbums(@Query("userId") userId: Int): Call<List<AlbumDto>>

}
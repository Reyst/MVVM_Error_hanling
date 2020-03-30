package gsihome.reyst.mvvm.example.di

import android.app.Activity
import com.google.gson.GsonBuilder
import gsihome.reyst.mvvm.example.R
import gsihome.reyst.mvvm.example.data.album.AlbumApi
import gsihome.reyst.mvvm.example.data.album.DefaultAlbumRepository
import gsihome.reyst.mvvm.example.data.album.RemoteAlbumDataSource
import gsihome.reyst.mvvm.example.data.album.RetrofitAlbumDataSource
import gsihome.reyst.mvvm.example.data.user.DefaultUserRepository
import gsihome.reyst.mvvm.example.data.user.RemoteUserDataSource
import gsihome.reyst.mvvm.example.data.user.RetrofitUserDataSource
import gsihome.reyst.mvvm.example.data.user.UserApi
import gsihome.reyst.mvvm.example.domain.repository.AlbumRepository
import gsihome.reyst.mvvm.example.domain.repository.UserRepository
import gsihome.reyst.mvvm.example.network.RetrofitManager
import gsihome.reyst.mvvm.example.ui.MainViewModel
import gsihome.reyst.mvvm.example.ui.albums.AlbumsViewModel
import gsihome.reyst.mvvm.example.ui.photos.PhotosViewModel
import gsihome.reyst.mvvm.example.ui.users.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val systemModule = module {
    single { androidContext().getSharedPreferences("preferences", Activity.MODE_PRIVATE) }
    single { GsonBuilder().create() }
    single {
        RetrofitManager(
            androidContext().getString(R.string.base_url),
            get(),
            mapOf(/*"Accept" to "application/hal+json"*/)
        )
    }
}

val viewModelModule = module {
    viewModel { MainViewModel() } // get()

    viewModel { PhotosViewModel() } // get()
}

val albumModule = module {

    viewModel { AlbumsViewModel(get()) }

    single { get<RetrofitManager>().getService(AlbumApi::class.java) }
    single<RemoteAlbumDataSource> { RetrofitAlbumDataSource(get()) }
    single<AlbumRepository> { DefaultAlbumRepository(get()) }

}

val userModule = module {

    viewModel { UsersViewModel(get()) } //

    single { get<RetrofitManager>().getService(UserApi::class.java) }
    single<RemoteUserDataSource> { RetrofitUserDataSource(get()) }
    single<UserRepository> { DefaultUserRepository(get()) }
}

/*
val imageLoaderModule = module {
    single<ImageLoader> { GlideImageLoader(androidApplication()) }
}

val restaurantModule = module {
    single { get<RetrofitManager>().getService(RestaurantApi::class.java) }
    single<RemoteRestaurantDataSource> { RetrofitRemoteRestaurantDataSource(get()) }
    single<RestaurantRepository> { DefaultRestaurantRepository(get()) }

    viewModel { ChooseRestaurantViewModel(get()) }
}

val authModule = module {
    single<AuthRepository> { DefaultAuthRepository(get()) }
    single<AuthDataSource> { FakeAuthDataSource() }

    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { NewPasswordViewModel(get()) }
}

val systemState = module {
    single<InternetStateObservable> { SystemEventHolder }
    single<InternetStateUpdatable> { SystemEventHolder }
}

*/

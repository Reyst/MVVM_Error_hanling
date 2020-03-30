package gsihome.reyst.mvvm.example.ui.photos

import androidx.fragment.app.Fragment
import gsihome.reyst.mvvm.example.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment: Fragment(R.layout.list_fragment) {

    private val viewModel:  PhotosViewModel by viewModel()

}
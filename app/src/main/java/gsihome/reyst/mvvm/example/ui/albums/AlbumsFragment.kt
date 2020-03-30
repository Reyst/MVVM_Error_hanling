package gsihome.reyst.mvvm.example.ui.albums

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import gsihome.reyst.mvvm.example.R
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.Album
import gsihome.reyst.mvvm.example.ui.MainViewModel
import kotlinx.android.synthetic.main.list_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment(R.layout.list_fragment) {

    private val viewModel: AlbumsViewModel by viewModel()
    private val sharedViewModel: MainViewModel by sharedViewModel()

    private val adapter = AlbumsAdapter { sharedViewModel.selectAlbum(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.list.layoutManager = LinearLayoutManager(requireContext())
        view.list.adapter = adapter

        viewModel.albums.observe(
            viewLifecycleOwner,
            { handleResource(it) }
        )

        viewModel.loadAlbums(sharedViewModel.selectedUser.value)

    }

    private fun handleResource(resource: Resource<List<Album>>) {
        when (resource) {
            is Resource.Success -> setAdapterData(resource.data ?: emptyList())
            is Resource.Error -> handleError(resource)
            else -> Unit
        }
    }

    private fun handleError(error: Resource.Error<List<Album>>) {
        if (!error.isHandled) {
            AlertDialog.Builder(requireContext())
                .setMessage(error.error?.message ?: "")
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    findNavController().navigateUp()
                    error.isHandled = true
                }
                .show()
        }
    }

    private fun setAdapterData(list: List<Album>) {
        adapter.setItems(list, sharedViewModel.selectedAlbum.value)
    }


}
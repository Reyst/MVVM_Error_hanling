package gsihome.reyst.mvvm.example.ui.users

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import gsihome.reyst.mvvm.example.R
import gsihome.reyst.mvvm.example.data.Resource
import gsihome.reyst.mvvm.example.domain.data.User
import gsihome.reyst.mvvm.example.ui.MainViewModel
import kotlinx.android.synthetic.main.list_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment(R.layout.list_fragment) {

    private val viewModel: UsersViewModel by viewModel()
    private val sharedViewModel: MainViewModel by sharedViewModel()

    private val adapter = UsersAdapter { sharedViewModel.selectUser(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.list.layoutManager = LinearLayoutManager(requireContext())
        view.list.adapter = adapter

        viewModel.users.observe(
            viewLifecycleOwner,
            { handleResource(it) }
        )

        val userValue = viewModel.users.value
        if (userValue == null || (userValue is Resource.Error && userValue.isHandled))
            viewModel.loadUsers()
    }

    private fun handleResource(resource: Resource<List<User>>) {
        when (resource) {
            is Resource.Success -> setAdapterData(resource.data ?: emptyList())
            is Resource.Error -> handleError(resource)
            else -> Unit
        }
    }

    private fun handleError(error: Resource.Error<List<User>>) {
        if (!error.isHandled) {
            AlertDialog.Builder(requireContext())
                .setMessage(error.error?.message ?: "")
                .setPositiveButton(android.R.string.ok) { _, _ -> error.isHandled = true }
                .show()
        }
    }

    private fun setAdapterData(list: List<User>) {
        adapter.setItems(list, sharedViewModel.selectedUser.value)
    }

}
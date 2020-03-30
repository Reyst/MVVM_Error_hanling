package gsihome.reyst.mvvm.example.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gsihome.reyst.mvvm.example.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: UserRepository
): ViewModel() {

    val users = repository.users

    fun loadUsers() {

        viewModelScope.launch {
            repository.loadUsers()
        }

    }

}
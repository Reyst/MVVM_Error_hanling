package gsihome.reyst.mvvm.example.ui.users

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gsihome.reyst.mvvm.example.R
import gsihome.reyst.mvvm.example.domain.data.User
import gsihome.reyst.mvvm.example.ui.utils.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(
    val onSelect: (User?) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserVH>() {

    private val items = mutableListOf<User>()

    private var selectedIndex: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        return parent
            .inflate(R.layout.item_user)
            .let { UserVH(it) { index -> select(index) } }
    }

    private fun select(index: Int) {

        val oldIndex = selectedIndex.takeIf { it >= 0 }
        selectedIndex = index

        oldIndex?.let { notifyItemChanged(it) }
        selectedIndex.takeIf { it >= 0 }
            ?.let {
                notifyItemChanged(it)
                onSelect(items[it])
            }
            ?: onSelect(null)
    }

    fun setItems(items: Collection<User>, selectedUser: User?) {
        this.items.clear()
        this.items.addAll(items)

        selectedIndex = selectedUser?.let { this.items.indexOf(selectedUser) } ?: -1

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.bind(items[position], position == selectedIndex)
    }

    class UserVH(
        itemView: View,
        onItemSelect: (index: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemSelect(adapterPosition) }
        }

        fun bind(user: User, isSelected: Boolean) {
            itemView.setBackgroundResource(
                if (isSelected) R.color.item_selected_color
                else R.color.item_color
            )
            itemView.name.text = user.name
            itemView.username.text = user.username
        }
    }
}

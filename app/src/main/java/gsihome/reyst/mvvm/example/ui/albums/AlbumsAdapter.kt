package gsihome.reyst.mvvm.example.ui.albums

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gsihome.reyst.mvvm.example.R
import gsihome.reyst.mvvm.example.domain.data.Album
import gsihome.reyst.mvvm.example.ui.utils.inflate
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumsAdapter(
    val onSelect: (Album?) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.AlbumVH>() {

    private val items = mutableListOf<Album>()

    private var selectedIndex: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        return parent
            .inflate(R.layout.item_album)
            .let { AlbumVH(it) { index -> select(index) } }
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

    fun setItems(items: Collection<Album>, selectedAlbum: Album?) {
        this.items.clear()
        this.items.addAll(items)

        selectedIndex = selectedAlbum?.let { this.items.indexOf(selectedAlbum) } ?: -1

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        holder.bind(items[position], position == selectedIndex)
    }

    class AlbumVH(
        itemView: View,
        onItemSelect: (index: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemSelect(adapterPosition) }
        }

        fun bind(album: Album, isSelected: Boolean) {
            itemView.setBackgroundResource(
                if (isSelected) R.color.item_selected_color
                else R.color.item_color
            )

            itemView.name.text = album.name
        }
    }

}

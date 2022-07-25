import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.gb.bufet.R
import ru.gb.bufet.databinding.ItemGalleryBinding
import ru.gb.bufet.model.responseData.RestaurantPicture

class GalleryAdapter(private val imagesList: List<RestaurantPicture>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(imageUrl: String) {
            //check images
            ContextCompat.getDrawable(binding.root.context, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(binding.root.context, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(imageUrl).error(it).placeholder(it1).into(binding.itemGalleryImage)
                } }
        }
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        imagesList[position].imagePath?.let { holder.setData(it) }
    }
}
package ru.gb.bufet.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import ru.gb.bufet.R

class GalleryAdapter (private val context: Context, private var imageList: ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =  (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.item_gallery, null)
        val image = view.findViewById<ImageView>(R.id.itemGalleryImage)

        ContextCompat.getDrawable(context, R.drawable.image_not_found)
            ?.let { ContextCompat.getDrawable(context, R.drawable.image_not_found)?.let { it1 ->
                Picasso.get().load(imageList[position]).error(it).placeholder(it1).into(image)
            } }

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}
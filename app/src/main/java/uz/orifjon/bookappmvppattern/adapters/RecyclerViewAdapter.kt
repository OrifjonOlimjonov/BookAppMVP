package uz.orifjon.bookappmvppattern.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.orifjon.bookappmvppattern.R
import uz.orifjon.bookappmvppattern.databinding.ItemRvBinding
import uz.orifjon.bookappmvppattern.models.Book
import java.lang.Exception

class RecyclerViewAdapter(var itemClick: (Book, Int, ImageView) -> Unit) :
    ListAdapter<Book, RecyclerViewAdapter.VH>(MyDiffUtils()) {

    inner class VH(var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(book: Book, position: Int) {
            binding.tv.text = book.title
            Picasso.get().load(book.book_image).placeholder(R.drawable.place_holder).into(binding.imageView, object :
                Callback {
                override fun onSuccess() {
                    itemView.setOnClickListener {
                        itemClick(book,position,binding.imageView)
                    }
                }
                override fun onError(e: Exception?) {}
            })
            binding.imageView.transitionName = "image_big$position";

        }
    }

    class MyDiffUtils : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.primary_isbn13 == newItem.primary_isbn13
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), position)
    }
}
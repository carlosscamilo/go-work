package com.example.gowork.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gowork.R
import com.example.gowork.views.models.Book

class BooksAdapter(private val books: List<Book>) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_book_title)
        private val authors: TextView = itemView.findViewById(R.id.tv_book_authors)
        private val description: TextView = itemView.findViewById(R.id.tv_book_description)

        fun bind(book: Book) {
            title.text = book.title
            authors.text = book.authors
            description.text = book.description
        }
    }
}

package com.example.gowork.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gowork.data.BookRepository
import com.example.gowork.models.Book
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookViewModel : ViewModel() {

    private val repository = BookRepository()

    var books: List<Book> = emptyList()
        private set

    fun searchBooks(query: String, category: String? = null) {
        viewModelScope.launch {
            try {
                books = withContext(Dispatchers.IO) {
                    repository.searchBooks(query, category)
                }
            } catch (e: Exception) {
                // Tratar erro
            }
        }
    }
}

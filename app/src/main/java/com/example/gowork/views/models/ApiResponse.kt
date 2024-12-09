package com.example.gowork.models

data class ApiResponse(
    val docs: List<BookResponse>
)

data class BookResponse(
    val title: String?,
    val author_name: List<String>?,
    val cover_i: Int?,
    val description: String?
)

fun BookResponse.toBook(): Book {
    return Book(
        title = this.title ?: "Título desconhecido",
        author = this.author_name?.joinToString(", ") ?: "Autor desconhecido",
        coverUrl = "https://covers.openlibrary.org/b/id/${this.cover_i}-L.jpg", // URL para a capa
        description = this.description ?: "Descrição não disponível"
    )
}

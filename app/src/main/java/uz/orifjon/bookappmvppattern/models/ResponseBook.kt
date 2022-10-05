package uz.orifjon.bookappmvppattern.models

data class ResponseBook(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
)
package uz.orifjon.bookappmvppattern.presenter

import uz.orifjon.bookappmvppattern.models.Book


interface BookService {


    fun showBooks(list: ArrayList<Book>)

}
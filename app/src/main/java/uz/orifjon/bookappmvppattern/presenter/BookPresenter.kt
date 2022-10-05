package uz.orifjon.bookappmvppattern.presenter

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.orifjon.bookappmvppattern.models.Book
import uz.orifjon.bookappmvppattern.retrofit.ApiClient
import uz.orifjon.bookappmvppattern.retrofit.ApiService

@OptIn(DelicateCoroutinesApi::class)
class BookPresenter(private val bookService: BookService) {

    private val apiClient = ApiClient.getRetrofit()
    private val apiService = apiClient.create(ApiService::class.java)
    private var list = ArrayList<Book>()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            list = apiService.listCoroutine().results.lists[0].books as ArrayList<Book>
            list = listAddAll(
                list,
                apiService.listCoroutine().results.lists[1].books as ArrayList<Book>
            )
            list = listAddAll(
                list,
                apiService.listCoroutine().results.lists[2].books as ArrayList<Book>
            )
            bookService.showBooks(list)
        }
    }


    fun showBooks() {
        bookService.showBooks(list)
    }

    private fun listAddAll(list: ArrayList<Book>, addList: ArrayList<Book>): ArrayList<Book> {

        for (i in 0 until addList.size) {
            list.add(addList[i])
        }
        return list
    }

}
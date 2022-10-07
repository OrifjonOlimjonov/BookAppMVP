package uz.orifjon.bookappmvppattern.presenter

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.orifjon.bookappmvppattern.database.BookData
import uz.orifjon.bookappmvppattern.database.BookDatabase
import uz.orifjon.bookappmvppattern.fragments.MainFragment
import uz.orifjon.bookappmvppattern.models.Book
import uz.orifjon.bookappmvppattern.models.ResponseBook
import uz.orifjon.bookappmvppattern.retrofit.ApiClient
import uz.orifjon.bookappmvppattern.retrofit.ApiService

class BookPresenterRetrofit(var context: Context) {

    private val apiClient = ApiClient.getRetrofit()
    private val apiService = apiClient.create(ApiService::class.java)
    private var list = ArrayList<Book>()

    init {
//        GlobalScope.launch(Dispatchers.IO) {
//            list = apiService.listCoroutine().results.lists[0].books as ArrayList<Book>
//            list = listAddAll(
//                list,
//                apiService.listCoroutine().results.lists[1].books as ArrayList<Book>
//            )
//            list = listAddAll(
//                list,
//                apiService.listCoroutine().results.lists[2].books as ArrayList<Book>
//            )
//            bookService.showBooks(list)
//        }
        apiService.list().enqueue(object : Callback<ResponseBook> {
            override fun onResponse(call: Call<ResponseBook>, response: Response<ResponseBook>) {
                if (BookDatabase.getDatabase(context).bookDao().listNotThread().isNotEmpty()) {
                    BookDatabase.getDatabase(context).bookDao().deleteTable()
                }
                BookDatabase.getDatabase(context).bookDao()
                    .add(BookData(0, Gson().toJson(response.body()!!.results.lists[0].books)))
            }

            override fun onFailure(call: Call<ResponseBook>, t: Throwable) {

            }

        })
    }


    private fun listAddAll(list: ArrayList<Book>, addList: ArrayList<Book>): ArrayList<Book> {

        for (i in 0 until addList.size) {
            list.add(addList[i])
        }
        return list
    }

}
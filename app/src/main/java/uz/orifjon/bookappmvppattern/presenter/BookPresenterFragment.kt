package uz.orifjon.bookappmvppattern.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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

class BookPresenterFragment(var bookService: BookService,var context: Context) {

    private lateinit var list:ArrayList<BookData>
    fun showBooks(){
        list = arrayListOf()
        val type = object : TypeToken<List<Book>>(){}.type
       BookDatabase.getDatabase(context).bookDao().list().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe {
                list = it as ArrayList<BookData>
               if(list.isNotEmpty())
               bookService.showBooks(Gson().fromJson(list[0].books,type))
            }


    }

}
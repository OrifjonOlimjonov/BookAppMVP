package uz.orifjon.bookappmvppattern.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.orifjon.bookappmvppattern.models.ResponseBook

interface ApiService {



    @GET("svc/books/v3/lists/full-overview.json?api-key=mHEoeq4V7sBUGzMCBFJWg0aYY0039j5M")
    suspend fun listCoroutine(): ResponseBook


}
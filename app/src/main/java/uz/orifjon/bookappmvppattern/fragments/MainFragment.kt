package uz.orifjon.bookappmvppattern.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.orifjon.bookappmvppattern.R
import uz.orifjon.bookappmvppattern.adapters.RecyclerViewAdapter
import uz.orifjon.bookappmvppattern.databinding.FragmentMainBinding
import uz.orifjon.bookappmvppattern.models.Book
import uz.orifjon.bookappmvppattern.models.ResponseBook
import uz.orifjon.bookappmvppattern.presenter.BookPresenter
import uz.orifjon.bookappmvppattern.presenter.BookService
import uz.orifjon.bookappmvppattern.retrofit.ApiClient
import uz.orifjon.bookappmvppattern.retrofit.ApiService

class MainFragment : Fragment(), BookService {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val bookPresenter = BookPresenter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = RecyclerViewAdapter { book, i, imageView ->
            val bundle = Bundle()
            bundle.putSerializable("book",book)
            val extras: FragmentNavigator.Extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(imageView, "image_big")
                .build()
            findNavController().navigate(
                R.id.action_mainFragment_to_viewFragment,
                bundle,
                null,
                extras
            )
        }
        binding.rv.adapter = adapter

        bookPresenter.showBooks()




        return binding.root
    }

    override fun showBooks(list: ArrayList<Book>) {
        adapter.submitList(list)
    }


}

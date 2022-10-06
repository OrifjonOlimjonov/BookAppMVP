package uz.orifjon.bookappmvppattern.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
import kotlin.coroutines.CoroutineContext

class MainFragment : Fragment(), BookService  {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    companion object {
        private lateinit var bookPresenter: BookPresenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        adapter = RecyclerViewAdapter { book, i, imageView ->
            val bundle = Bundle()
            bundle.putParcelable("book", book)
            findNavController().navigate(
                R.id.action_mainFragment_to_viewFragment,
                bundle,
                null,
                FragmentNavigatorExtras(imageView to "image_big")
            )
        }
        binding.rv.adapter = adapter

        bookPresenter = BookPresenter(this)
        bookPresenter.showBooks()




        return binding.root
    }

    override fun showBooks(list: ArrayList<Book>) {
        GlobalScope.launch(Dispatchers.Main) {
            if (list.size > 0) {
                binding.progress.visibility = View.INVISIBLE
                binding.rv.visibility = View.VISIBLE
            }
        }

        adapter.submitList(list)
    }


}

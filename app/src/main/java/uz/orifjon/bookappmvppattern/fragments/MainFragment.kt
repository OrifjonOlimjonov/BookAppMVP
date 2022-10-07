package uz.orifjon.bookappmvppattern.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.orifjon.bookappmvppattern.R
import uz.orifjon.bookappmvppattern.adapters.RecyclerViewAdapter
import uz.orifjon.bookappmvppattern.databinding.FragmentMainBinding
import uz.orifjon.bookappmvppattern.models.Book
import uz.orifjon.bookappmvppattern.presenter.BookPresenterFragment
import uz.orifjon.bookappmvppattern.presenter.BookService

class MainFragment : Fragment(), BookService {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter


    private lateinit var bookPresenter: BookPresenterFragment


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






        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookPresenter = BookPresenterFragment(this,requireContext())
        bookPresenter.showBooks()
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

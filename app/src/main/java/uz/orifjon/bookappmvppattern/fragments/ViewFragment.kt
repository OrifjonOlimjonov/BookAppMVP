package uz.orifjon.bookappmvppattern.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.orifjon.bookappmvppattern.R
import uz.orifjon.bookappmvppattern.databinding.FragmentViewBinding
import uz.orifjon.bookappmvppattern.models.Book

class ViewFragment : Fragment() {

    private lateinit var binding: FragmentViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentViewBinding.inflate(inflater,container,false)

        val book = arguments?.getSerializable("book") as Book

        Picasso.get().load(book.book_image).placeholder(R.drawable.place_holder).into(binding.imageView, object :
            Callback {
            override fun onSuccess() {

            }
            override fun onError(e: Exception?) {}
        })


        return binding.root
    }


}
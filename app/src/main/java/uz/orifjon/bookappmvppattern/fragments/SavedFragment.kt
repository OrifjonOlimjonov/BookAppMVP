package uz.orifjon.bookappmvppattern.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.orifjon.bookappmvppattern.R
import uz.orifjon.bookappmvppattern.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)



        return binding.root
    }


}
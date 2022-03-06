package com.example.fragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fragmentnavigation.databinding.FragmentFirstBinding
import com.example.fragmentnavigation.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            // navigation ekranında oluşturdugumuz aksiyon
            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment2()
            Navigation.findNavController(it).navigate(action)
        }
        //atgument varmı yokmu kontrol etmek için varsa işlemleri yap
        arguments?.let {
            val kullaniAdi = SecondFragmentArgs.fromBundle(it).username
            binding.textView2.text = kullaniAdi

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
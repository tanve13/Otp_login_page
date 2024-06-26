package com.tanveer.otptask

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.tanveer.otptask.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSecondBinding? = null
    var email = ""
    var otp = ""
    var mainActivity: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            mainActivity = activity as MainActivity
            email = it.getString("email") ?: ""
             otp = it.getString("otp") ?: ""
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.etEnteredEmail?.setText(email)
        binding?.et1?.doOnTextChanged { _, _, _, _ ->
            val otp = binding?.et1?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et2?.requestFocus()
            }
        }
        binding?.et2?.doOnTextChanged { _, _, _, _ ->
                val otp = binding?.et1?.text?.toString() ?: ""
                if (otp.length == 1) {
                    binding?.et3?.requestFocus()
                }
        }
        binding?.et2?.doAfterTextChanged {
            val otp = binding?.et2?.text?.toString()?: ""
            if(otp.length == 0) {
                binding?.et1?.requestFocus()
            }
        }
        binding?.et3?.doOnTextChanged { _, _, _, _ ->
            val otp = binding?.et3?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et4?.requestFocus()
            }
        }
        binding?.et3?.doAfterTextChanged {
            val otp = binding?.et3?.text?.toString()?: ""
            if(otp.length == 0) {
                binding?.et2?.requestFocus()
            }
        }
        binding?.et4?.doAfterTextChanged {
            val otp = binding?.et4?.text?.toString()?: ""
            if(otp.length == 0) {
                binding?.et3?.requestFocus()
            }
        }
        binding?.btnVerify?.setOnClickListener {
           var number = "${binding?.et1?.text?.toString()}${binding?.et2?.text?.toString()}${binding?.et3?.text?.toString()}${binding?.et4?.text?.toString()}"
           if(number==otp){
            Dialog(requireContext()).apply {
                setContentView(R.layout.custom_layout)
                show()
            }
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            }
            else {
            Dialog(requireContext()).apply {
                setContentView(R.layout.custom_layout2)
                show()
            }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
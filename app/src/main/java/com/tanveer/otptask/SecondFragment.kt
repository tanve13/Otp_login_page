package com.tanveer.otptask

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var mainActivity: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            mainActivity = activity as MainActivity
            email = it.getString("email") ?: ""
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
            var otp = binding?.et1?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et2?.requestFocus()
            }
        }
        binding?.et1?.onEditorAction(0)
        binding?.et2?.doOnTextChanged { _, _, _, _ ->
            var otp = binding?.et1?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et3?.requestFocus()
            } else {
                binding?.et2?.requestFocus()
            }
        }
        binding?.et2?.onEditorAction(0)
        binding?.et3?.doOnTextChanged { _, _, _, _ ->
            var otp = binding?.et1?.text?.toString() ?: ""
            if (otp.length == 1) {
                binding?.et4?.requestFocus()
            } else {
                binding?.et3?.requestFocus()
            }
        }
        binding?.btnVerify?.setOnClickListener {
            /* if () {
            Dialog(requireContext()).apply {
                setContentView(R.id.tvCongratulations)
                show()
            }*/
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            }

           /* else {
            Dialog(requireContext()).apply {
                setContentView(R.id.tvSorry)
                show()
            }
            }
        }*/

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
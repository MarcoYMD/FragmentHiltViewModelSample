package com.example.example.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import coil.api.load
import com.example.example.R
import com.example.example.presentation.viewmodel.MessageState
import com.example.example.presentation.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Receiver.newInstance] factory method to
 * create an instance of this fragment.
 */
class Receiver : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private lateinit var msgTextView: TextView
    private lateinit var imageView: ImageView
    //    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
////        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_receiver, container, false)
        msgTextView = view.findViewById(R.id.receiver_tv) as TextView
        imageView = view.findViewById(R.id.receiver_iv) as ImageView
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model.getMessage.observe(viewLifecycleOwner) {
            when (it) {
                is MessageState.Loading -> updateTextView("Loading")
                is MessageState.Finish -> {
                    updateTextView(it.data.quote)
                    updateBackgroundView(it.data.background)
                }
                is MessageState.Error -> showErrorView(it.msg)
            }
        }

    }

    private fun showErrorView(msg: String?) {
        updateTextView(R.string.placeholder_textview)
        view?.let {
            Snackbar.make(
                it,
                msg.toString(),
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction("Dismiss") {}
                .show()
        }
    }

    private fun updateBackgroundView(url: String?) {
        imageView.load(url)
    }

    private fun updateTextView(@StringRes resId: Int) {
        msgTextView.setText(resId)
    }

    private fun updateTextView(msg: String?) {
        msgTextView.text = msg.toString()
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Receiver.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Receiver().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
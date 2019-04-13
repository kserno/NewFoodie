package app.kserno.foodie.android.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import app.kserno.foodie.android.R
import kotlinx.android.synthetic.main.dialog_info.*




/**
 *  Created by filipsollar on 2019-04-13
 */
class InfoDialog: DialogFragment() {

    companion object {
        fun create(title: String, text: String): InfoDialog {
            val bundle = Bundle().apply {
                putString("title", title)
                putString("text", text)
            }
            return InfoDialog().apply { arguments = bundle }
        }
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.d_width)
        val height = resources.getDimensionPixelSize(R.dimen.d_height)
        dialog!!.window!!.setLayout(width, height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = arguments?.getString("title")
        tvText.text = arguments?.getString("text")

        btOk.setOnClickListener {
            dismiss()
        }
    }
}
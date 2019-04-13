package com.kserno.foodie.addfood

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kserno.foodie.R
import com.kserno.foodie.databinding.FragmentAddFoodBinding
import android.app.Activity.RESULT_OK
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.dialog.LoadingDialog
import kotlinx.android.synthetic.main.fragment_add_food.*
import java.io.File
import java.net.URI
import javax.inject.Inject


/**
 *  Created by filipsollar on 2019-04-03
 */
class AddFoodFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_add_food


    @Inject lateinit var api: Api

    lateinit var viewModel: AddFoodViewModel
    lateinit var binding: FragmentAddFoodBinding

    var dialog: DialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        val args = AddFoodFragmentArgs.fromBundle(arguments!!)

        binding = DataBindingUtil.bind(view)!!
        viewModel = AddFoodViewModel(api, args.categoryId)
        binding.viewModel = viewModel

        viewModel.actionPickPhoto.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_PICK
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
            }
        })
        viewModel.actionDone.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                dialog?.dismiss()
                findNavController().navigateUp()
            }
        })
        viewModel.uploading.observe(this, Observer {
            if (it) {
                dialog = LoadingDialog()
                dialog?.show(fragmentManager!!, "0")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            val imageFile = File(getPath(selectedImageUri))
            ivFood.setImageURI(selectedImageUri)
            viewModel.photo.value = imageFile
        }
    }

    fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context!!.getContentResolver().query(uri, projection, null, null, null) ?: return null
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s = cursor.getString(column_index)
        cursor.close()
        return s
    }


}
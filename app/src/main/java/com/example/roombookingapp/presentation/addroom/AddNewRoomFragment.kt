package com.example.roombookingapp.presentation.addroom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.roombookingapp.R
import com.example.roombookingapp.constants.TAG_USER_ID
import com.example.roombookingapp.constants.TAG_USER_TOKEN
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import com.example.roombookingapp.presentation.utils.extensions.showSnackBarWithAction
import com.google.android.material.color.MaterialColors
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddNewRoomFragment : Fragment() {

    companion object {
        fun newInstance(userID: String, userToken: String): AddNewRoomFragment {
            val args = Bundle()
            args.putString(TAG_USER_ID, userID)
            args.putString(TAG_USER_TOKEN, userToken)
            val fragment = AddNewRoomFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val vmRoomAdd: AddNewRoomViewModel by viewModel {
        val userID = arguments?.getString(TAG_USER_ID)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        parametersOf(userID, userToken)
    }

    private lateinit var tbRoomAdd: Toolbar
    private lateinit var etRoomId: TextInputEditText
    private lateinit var etRoomType: TextInputEditText
    private lateinit var etRoomCapacity: TextInputEditText
    private lateinit var etRoomFloor: TextInputEditText
    private lateinit var etRoomDescription: TextInputEditText
    private lateinit var btnRoomCreate: AppCompatButton
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentContext = context ?: return

        initViews(view)
        initToolbar()
        initTextChangeListeners()
        initClickListeners(currentContext)
        initObservers(currentContext)
    }

    private fun initViews(view: View) {
        with(view) {
            tbRoomAdd = findViewById(R.id.room_add_toolbar)
            etRoomId = findViewById(R.id.room_add_et_room_id)
            etRoomType = findViewById(R.id.room_add_et_room_type)
            etRoomCapacity = findViewById(R.id.room_add_et_room_capacity)
            etRoomFloor = findViewById(R.id.room_add_et_room_floor)
            etRoomDescription = findViewById(R.id.room_add_et_room_description)
            btnRoomCreate = findViewById(R.id.room_add_btn_create_room)
            progressIndicator = findViewById(R.id.room_add_progress_indicator)
        }
    }

    private fun initToolbar() {
        tbRoomAdd.title = getString(R.string.tb_title_add_new_room)
        tbRoomAdd.setNavigationIcon(R.drawable.iv_close)
        tbRoomAdd.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initTextChangeListeners() {
        etRoomId.addTextChangedListener { roomId ->
            vmRoomAdd.roomIdLiveData.value = roomId.toString()
        }

        etRoomType.addTextChangedListener { roomType ->
            vmRoomAdd.roomTypeLiveData.value = roomType.toString()
        }

        etRoomCapacity.addTextChangedListener { roomCapacity ->
            vmRoomAdd.roomCapacityLiveData.value = roomCapacity.toString()
        }

        etRoomFloor.addTextChangedListener { roomFloor ->
            vmRoomAdd.roomFloorLiveData.value = roomFloor.toString()
        }

        etRoomDescription.addTextChangedListener { roomDescription ->
            vmRoomAdd.roomDescriptionLiveData.value = roomDescription.toString()
        }
    }

    private fun initClickListeners(currentContext: Context) {
        btnRoomCreate.setOnClickListener {
            if (etRoomId.text.toString().isEmpty()
                || etRoomType.text.toString().isEmpty()
                || etRoomCapacity.text.toString().isEmpty()
                || etRoomFloor.text.toString().isEmpty()
                || etRoomDescription.text.toString().isEmpty()
            ) {
                currentContext.showSnackBar(
                    view = it,
                    messageStringId = R.string.please_fill_out_all_required_fields
                )
            } else {
                vmRoomAdd.createRoom()
            }
        }
    }

    private fun initObservers(currentContext: Context) {
        vmRoomAdd.progressLiveData.observe(viewLifecycleOwner) { isInProgress ->
            if (isInProgress) {
                btnRoomCreate.isEnabled = false
                btnRoomCreate.setTextColor(resources.getColor(R.color.ui_03, null))
                progressIndicator.visibility = View.VISIBLE
            } else {
                btnRoomCreate.isEnabled = true
                btnRoomCreate.setTextColor(MaterialColors.getColor(btnRoomCreate, R.attr.ui_01))
                progressIndicator.visibility = View.INVISIBLE
            }
        }

        vmRoomAdd.roomCreateStatusLiveData.observe(viewLifecycleOwner) { isRegistered ->
            if (isRegistered) {
                currentContext.showSnackBar(
                    view = btnRoomCreate,
                    messageStringId = R.string.room_created_successfully
                )
                parentFragmentManager.popBackStack()
            } else {
                currentContext.showSnackBarWithAction(
                    view = btnRoomCreate,
                    messageStringId = R.string.room_create_failed,
                    actionStringId = R.string.retry
                ) {
                    vmRoomAdd.createRoom()
                }
            }
        }
    }
}
package com.example.roombookingapp.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.roombookingapp.R
import com.example.roombookingapp.constants.TAG_USER_ID
import com.example.roombookingapp.constants.TAG_USER_ROLE
import com.example.roombookingapp.constants.TAG_USER_TOKEN
import com.example.roombookingapp.domain.models.SignInResponse
import com.example.roombookingapp.presentation.addroom.AddNewRoomFragment
import com.example.roombookingapp.presentation.allusers.AllUsersFragment
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsFragment
import com.example.roombookingapp.presentation.signin.SignInFragment
import com.example.roombookingapp.presentation.utils.ClickListener
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import com.example.roombookingapp.presentation.utils.extensions.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RoomsFragment : Fragment() {

    companion object {
        fun newInstance(userData: SignInResponse): RoomsFragment {
            val args = Bundle()
            args.putLong(TAG_USER_ID, userData.userId)
            args.putString(TAG_USER_ROLE, userData.role)
            args.putString(TAG_USER_TOKEN, userData.jwtToken)

            val fragment = RoomsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val flContainerID = R.id.fl_login_container

    private val vmRooms: RoomsViewModel by viewModel {
        val userId = arguments?.getLong(TAG_USER_ID)
        val userRole = arguments?.getString(TAG_USER_ROLE)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        parametersOf(userId, userRole, userToken)
    }

    private lateinit var userId: String
    private lateinit var userRole: String
    private lateinit var userToken: String

    private lateinit var tbRooms: Toolbar
    private lateinit var srlRooms: SwipeRefreshLayout
    private lateinit var rvRooms: RecyclerView
    private lateinit var roomsAdapter: RoomsAdapter
    private lateinit var roomsLayoutManager: LinearLayoutManager
    private lateinit var roomsItemDecorator: SpaceItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = arguments?.getLong(TAG_USER_ID).toString()
        userRole = arguments?.getString(TAG_USER_ROLE) ?: return
        userToken = arguments?.getString(TAG_USER_TOKEN) ?: return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rooms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initToolbar()
        initRefreshListener()
        initRoomsRecyclerView()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            tbRooms = findViewById(R.id.rooms_toolbar)
            srlRooms = findViewById(R.id.rooms_swipe_refresh_layout)
            rvRooms = findViewById(R.id.rooms_rv_rooms)
        }
    }

    private fun initToolbar() {
        tbRooms.title = getString(R.string.tb_title_available_rooms)
        tbRooms.setNavigationIcon(R.drawable.iv_log_out)
        tbRooms.setNavigationOnClickListener {
            initSignPutAlertDialog()
        }
    }

    private fun initSignPutAlertDialog() {
        val currentContext = context ?: return
        AlertDialog.Builder(currentContext).setTitle(R.string.sign_out_w_question)
            .setPositiveButton(R.string.confirm) { _, _ ->
                initSignInFragment()
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
            }
            .show()
    }

    private fun initRefreshListener() {
        srlRooms.setOnRefreshListener {
            vmRooms.getRooms()
        }
    }

    private fun initRoomsRecyclerView() {
        roomsAdapter = RoomsAdapter(inflater = layoutInflater)
        roomsLayoutManager = LinearLayoutManager(context)
        roomsItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 8)

        rvRooms.apply {
            adapter = roomsAdapter
            layoutManager = roomsLayoutManager
            addItemDecoration(roomsItemDecorator)
        }

        roomsAdapter.listener = ClickListener { room ->
            initRoomDetailsFragment(roomId = room.id)
        }

        if (userRole == getString(R.string.user_role_admin)) {
            roomsAdapter.longListener = ClickListener { room ->
                initRoomDeleteAlertDialog(roomId = room.id)
            }
        }
    }

    private fun initRoomDetailsFragment(roomId: Long) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                flContainerID,
                RoomDetailsFragment.newInstance(
                    userID = userId,
                    userToken = userToken,
                    roomId = roomId.toString(),
                    userRole = userRole
                ),
                null
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initRoomDeleteAlertDialog(roomId: Long) {
        val currentContext = context ?: return
        AlertDialog.Builder(currentContext).setTitle(R.string.delete_this_room_w_question)
            .setPositiveButton(R.string.confirm) { _, _ ->
                vmRooms.deleteRoom(roomId = roomId)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
            }
            .show()
    }

    private fun initObservers() {
        vmRooms.roomsLiveData.observe(viewLifecycleOwner) { rooms ->
            roomsAdapter.submitList(rooms)
            srlRooms.isRefreshing = false
        }

        vmRooms.isUserAdmin.observe(viewLifecycleOwner) { isUserAdmin ->
            if (isUserAdmin) {
                initMenu()
            }
        }

        vmRooms.deleteRoomStatusLiveData.observe(viewLifecycleOwner) { status ->
            when (status) {
                0 -> {
                    context?.showSnackBar(
                        view = tbRooms,
                        messageStringId = R.string.room_deleted_successfully
                    )
                }

                1 -> {
                    context?.showSnackBar(
                        view = tbRooms,
                        messageStringId = R.string.room_delete_failed
                    )
                }
            }
            vmRooms.getRooms()
        }
    }

    private fun initMenu() {
        tbRooms.inflateMenu(R.menu.menu_room_add)
        tbRooms.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_all_users -> initAllUsersFragment()
                R.id.menu_room_add -> initAddNewRoomFragment()
            }
            true
        }
    }

    private fun initSignInFragment() {
        parentFragmentManager
            .beginTransaction()
            .add(flContainerID, SignInFragment())
            .commit()
    }

    private fun initAllUsersFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                flContainerID,
                AllUsersFragment.newInstance(
                    userID = userId,
                    userToken = userToken
                )
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initAddNewRoomFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                flContainerID,
                AddNewRoomFragment.newInstance(
                    userID = userId,
                    userToken = userToken
                ),
                null
            )
            .addToBackStack(null)
            .commit()
    }
}
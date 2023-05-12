package com.example.roombookingapp.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R
import com.example.roombookingapp.data.models.LoginResponse
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsFragment
import com.example.roombookingapp.presentation.utils.ClickListener
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import com.example.roombookingapp.presentation.utils.extensions.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val TAG_USER_ID = "TAG_USER_ID"
private const val TAG_USER_ROLE = "TAG_USER_ROLE"
private const val TAG_USER_TOKEN = "TAG_USER_TOKEN"

class RoomsFragment : Fragment() {

    companion object {
        fun newInstance(userData: LoginResponse): RoomsFragment {
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
        val userID = arguments?.getLong(TAG_USER_ID)
        val userRole = arguments?.getString(TAG_USER_ROLE)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        parametersOf(userID, userRole, userToken)
    }

    private lateinit var tbRooms: Toolbar
    private lateinit var rvRooms: RecyclerView
    private lateinit var roomsAdapter: RoomsAdapter
    private lateinit var roomsLayoutManager: LinearLayoutManager
    private lateinit var roomsItemDecorator: SpaceItemDecoration

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
        initRoomsRecyclerView()
        initObservers()
        initMenu()
    }

    private fun initViews(view: View) {
        with(view) {
            tbRooms = findViewById(R.id.rooms_toolbar)
            rvRooms = findViewById(R.id.rooms_rv_rooms)
        }
    }

    private fun initToolbar() {
        tbRooms.title = getString(R.string.available_rooms)
    }

    private fun initRoomsRecyclerView() {
        roomsAdapter = RoomsAdapter(inflater = layoutInflater)
        roomsLayoutManager = LinearLayoutManager(context)
        roomsItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)

        rvRooms.apply {
            adapter = roomsAdapter
            layoutManager = roomsLayoutManager
            addItemDecoration(roomsItemDecorator)
        }

        roomsAdapter.listener = ClickListener { room ->
            val userID = arguments?.getLong(TAG_USER_ID)
            val userToken = arguments?.getString(TAG_USER_TOKEN) ?: ""

            parentFragmentManager
                .beginTransaction()
                .replace(
                    flContainerID,
                    RoomDetailsFragment.newInstance(
                        userID = userID.toString(),
                        userToken = userToken,
                        roomId = room.id.toString()
                    ),
                    null
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initObservers() {
        vmRooms.roomsLiveData.observe(viewLifecycleOwner) { rooms ->
            roomsAdapter.submitList(rooms)
        }
    }

    private fun initMenu() {
        tbRooms.inflateMenu(R.menu.menu_room_add)
        tbRooms.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.menu_room_add) {
                initRoomAddFragment()
            }
            true
        }
    }

    private fun initRoomAddFragment() {
        context?.showToast(getString(R.string.menu_room_add))
    }
}
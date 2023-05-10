package com.example.roombookingapp.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.roomdetails.RoomDetailsFragment
import com.example.roombookingapp.presentation.utils.ClickListener
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsFragment : Fragment() {

    private val flContainerID = R.id.fl_login_container

    private val vmRooms: RoomsViewModel by viewModel()

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
        initRoomsRecyclerView()
        initObservers()
    }

    private fun initViews(view: View) {
        rvRooms = view.findViewById(R.id.rooms_rv_rooms)
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
            parentFragmentManager
                .beginTransaction()
                .replace(flContainerID, RoomDetailsFragment.newInstance(room.id), null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initObservers() {
        vmRooms.roomsLiveData.observe(viewLifecycleOwner) { rooms ->
            roomsAdapter.submitList(rooms)
        }
    }
}
package com.example.roombookingapp.presentation.rooms.allusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roombookingapp.R
import com.example.roombookingapp.presentation.utils.ClickListener
import com.example.roombookingapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val TAG_USER_ID = "TAG_USER_ID"
private const val TAG_USER_TOKEN = "TAG_USER_TOKEN"

class AllUsersFragment : Fragment() {

    companion object {
        fun newInstance(userID: String, userToken: String): AllUsersFragment {
            val args = Bundle()
            args.putString(TAG_USER_ID, userID)
            args.putString(TAG_USER_TOKEN, userToken)

            val fragment = AllUsersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val vmAllUsers: AllUsersViewModel by viewModel {
        val userID = arguments?.getString(TAG_USER_ID)
        val userToken = arguments?.getString(TAG_USER_TOKEN)
        parametersOf(userID, userToken)
    }

    private lateinit var tbAllUsers: Toolbar
    private lateinit var rvAllUsers: RecyclerView
    private lateinit var allUsersAdapter: AllUsersAdapter
    private lateinit var allUsersLayoutManager: LinearLayoutManager
    private lateinit var allUsersItemDecorator: SpaceItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initToolbar()
        initRoomsRecyclerView()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            tbAllUsers = findViewById(R.id.all_users_toolbar)
            rvAllUsers = findViewById(R.id.all_users_rv)
        }
    }

    private fun initToolbar() {
        tbAllUsers.title = getString(R.string.registered_users)
    }

    private fun initRoomsRecyclerView() {
        allUsersAdapter = AllUsersAdapter(inflater = layoutInflater)
        allUsersLayoutManager = LinearLayoutManager(context)
        allUsersItemDecorator = SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 8)

        rvAllUsers.apply {
            adapter = allUsersAdapter
            layoutManager = allUsersLayoutManager
            addItemDecoration(allUsersItemDecorator)
        }

        allUsersAdapter.listener = ClickListener { user ->
        }
    }

    private fun initObservers() {
        vmAllUsers.usersLiveData.observe(viewLifecycleOwner) { users ->
            allUsersAdapter.submitList(users)
        }
    }
}
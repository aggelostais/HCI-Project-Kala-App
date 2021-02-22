package ntua.hci.kalaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ntua.hci.kalaapp.R

class CategorizedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_categorized, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnAllTasks).setOnClickListener {
            findNavController().navigate(R.id.action_categorizedFragment_to_navigation_home)
        }

        view.findViewById<Button>(R.id.btnStarred).setOnClickListener {
            findNavController().navigate(R.id.action_categorizedFragment_to_starredFragment)
        }
    }
}
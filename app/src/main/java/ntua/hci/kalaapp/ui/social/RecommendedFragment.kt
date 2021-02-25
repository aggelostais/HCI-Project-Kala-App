package ntua.hci.kalaapp.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ntua.hci.kalaapp.R

class RecommendedFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_social_recommended, container, false)

        root.findViewById<TextView>(R.id.title_text).text = getString(R.string.title_social)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnYourFriends).setOnClickListener {
            findNavController().navigate(R.id.action_recommendedFragment_to_navigation_social)
        }
    }
}
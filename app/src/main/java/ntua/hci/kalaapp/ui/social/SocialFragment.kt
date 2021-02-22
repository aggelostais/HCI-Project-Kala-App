package ntua.hci.kalaapp.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ntua.hci.kalaapp.R

class SocialFragment : Fragment() {

    private lateinit var socialViewModel: SocialViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        socialViewModel = ViewModelProvider(this).get(SocialViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_social, container, false)
        val textView: TextView = root.findViewById(R.id.text_social)
        socialViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnRecommended).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_social_to_recommendedFragment)
        }
    }
}
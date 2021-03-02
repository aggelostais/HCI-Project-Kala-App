package ntua.hci.kalaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ntua.hci.kalaapp.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var layoutList: LinearLayout
    private lateinit var buttonAdd: android.widget.Button

    private var categoryList = mutableListOf<String>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        root.findViewById<TextView>(R.id.title_text).text = getString(R.string.title_home)

        categoryList.add("Κατηγορία A")
        categoryList.add("Κατηγορία B")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnCategorized).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_categorizedFragment)
        }

        view.findViewById<Button>(R.id.btnStarred).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_starredFragment)
        }

        layoutList = requireView().findViewById(R.id.layout_list)
        buttonAdd = requireView().findViewById(R.id.btnAddTask)

        var taskView = layoutInflater.inflate(R.layout.task_layout, null, false)

        var task_rating = taskView.findViewById<RatingBar>(R.id.TaskRating)
        task_rating.numStars = 5
        task_rating.rating = 2.0F

        var imageClose = taskView.findViewById<ImageView>(R.id.image_remove)

        imageClose.setOnClickListener{
            layoutList.removeView(taskView)
        }

        layoutList.addView(taskView)


//        buttonAdd.setOnClickListener{
//            var taskView = layoutInflater.inflate(R.layout.add_task_layout, null, false)
//
//            var editTask = taskView.findViewById<EditText>(R.id.edit_task_name)
//            var spinner = taskView.findViewById<AppCompatSpinner>(R.id.spinner)
//            var imageClose = taskView.findViewById<ImageView>(R.id.image_remove)
//
//            var arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categoryList)
//            spinner.adapter = arrayAdapter
//
//            imageClose.setOnClickListener{
//                layoutList.removeView(taskView)
//            }
//
//            layoutList.addView(taskView)
//        }

        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_newTaskFragment)
        }
    }
}
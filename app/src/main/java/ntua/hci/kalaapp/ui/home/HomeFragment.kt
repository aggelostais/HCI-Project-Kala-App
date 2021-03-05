package ntua.hci.kalaapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import ntua.hci.kalaapp.R
import java.lang.Integer.min

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

        root.findViewById<TextView>(R.id.title_text).text = getString(R.string.title_home)

        layoutList = root.findViewById(R.id.layout_list)
        buttonAdd = root.findViewById(R.id.btnAddTask)

        categoryList.add("Κατηγορία A")
        categoryList.add("Κατηγορία B")

        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("Tasks")

        myRef.get().addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//            Log.i("firebase", "Length = ${it.childrenCount}")
            var tasksFound = it.children
            tasksFound.forEach{ element ->
                var name = element.child("name").value.toString()
                println(name)
                var rating : Float = element.child("rating").value.toString().toFloat()
                var time : String = element.child("time").value.toString()
                var category : String = element.child("category").value.toString()
                var key : String = element.child("key").value.toString()
                var date : String = element.child("date").value.toString()

                var taskView = layoutInflater.inflate(R.layout.task_layout, null, false)

                //setup fields
                var task_name = taskView.findViewById<TextView>(R.id.task_name)
                task_name.text = name
                taskView.findViewById<RatingBar>(R.id.TaskRating).rating = rating
                taskView.findViewById<TextView>(R.id.time_label).text = time
                taskView.findViewById<TextView>(R.id.date_label).text = date
                taskView.findViewById<TextView>(R.id.category_name).text = category

                if(name.length > 11){
                    task_name.textSize = 20F
                    var max = 20
                    if(name.length < 20) max = name.length
                    task_name.text = name.subSequence(0, max)
                    task_name.setPadding(8, 20,0,0)
                }

                var imageClose = taskView.findViewById<ImageView>(R.id.image_remove)

                imageClose.setOnClickListener{
                    layoutList.removeView(taskView)

                    FirebaseDatabase.getInstance().getReference("Tasks").child(key).removeValue()
                }

                taskView.findViewById<ImageView>(R.id.image_complete).setOnClickListener {
                    layoutList.removeView(taskView)

                    FirebaseDatabase.getInstance().getReference("Tasks").child(key).removeValue()
                }

                layoutList.addView(taskView)

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

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
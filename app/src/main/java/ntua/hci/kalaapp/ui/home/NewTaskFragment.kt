package ntua.hci.kalaapp.ui.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ntua.hci.kalaapp.R
import java.util.*
import kotlin.math.log

class NewTaskFragment : Fragment() {

    private lateinit var nameText: TextView
    private lateinit var spinner: AppCompatSpinner

    private lateinit var dateText: TextView
    private lateinit var timeText: TextView
    private var mDate = 0
    private var mMonth = 0
    private var mYear = 0
    private var mHour = 0
    private var mMinute = 0

    private lateinit var name : String
    private lateinit var category: String

    private var categoryList = mutableListOf<String>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.new_task, container, false)

        root.findViewById<TextView>(R.id.title_text).text = getString(R.string.title_new_task)

        nameText = root.findViewById(R.id.edit_task_name)

        dateText = root.findViewById(R.id.date_picker)
        timeText = root.findViewById(R.id.time_picker)

        categoryList.add("Κατηγορία A")
        categoryList.add("Κατηγορία B")

        spinner = root.findViewById(R.id.spinner)

        var arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        spinner.adapter = arrayAdapter

        return root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateText.setOnClickListener{
            var cal : Calendar = Calendar.getInstance()
            mDate = cal.get(Calendar.DATE)
            mMonth = cal.get(Calendar.MONTH)
            mYear = cal.get(Calendar.YEAR)

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                mYear = year
                mMonth = monthOfYear
                mDate = dayOfMonth

                // Display Selected date in TextView
                dateText.text = "$dayOfMonth-$monthOfYear-$year"

            }, mYear, mMonth, mDate)
            dpd.show()
        }

        timeText.setOnClickListener{
            var cal : Calendar = Calendar.getInstance()
            mHour = cal.get(Calendar.HOUR_OF_DAY)
            mMinute = cal.get(Calendar.MINUTE)

            val dpd = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                mHour = hourOfDay
                mMinute = minute

                var hourtxt :String = if(mHour < 10) "0$mHour" else "$mHour"
                var minutetxt : String = if(mMinute < 10) "0$mMinute" else "$mMinute"

                // Display Selected date in TextView
                timeText.text = "$hourtxt:$minutetxt"

            }, mHour, mMinute, true)
            dpd.show()
        }

        view.findViewById<Button>(R.id.btnCreateTask).setOnClickListener {

            name = nameText.text.toString()
            category = spinner.selectedItem.toString()

            println(name)
            println(category)

            println(mDate)
            println(mMonth)
            println(mYear)

            println(mHour)
            println(mMinute)
        }
    }
}
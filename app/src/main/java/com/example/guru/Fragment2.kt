package com.example.guru

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var time = 0
    private var isRunning = false
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_2, container, false)

        val start_button = myView.findViewById<Button>(R.id.timer_start)
        val restart_button = myView.findViewById<Button>(R.id.timer_restart)

        start_button.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                timer = timer(period = 1000) {
                    time++

                    var sec = time % 60
                    var min = (time / 60) % 60
                    var hour = (time / 60) / 60

                    activity?.runOnUiThread {
                        myView.findViewById<TextView>(R.id.timer_sec).setText("$sec")
                        myView.findViewById<TextView>(R.id.timer_min).setText("$min")
                        myView.findViewById<TextView>(R.id.timer_hour).setText("$hour")
                    }
                }
            } else {
                timer?.cancel()
            }
        }
        restart_button.setOnClickListener {
            timer?.cancel()

            time = 0
            isRunning = false
            activity?.runOnUiThread {
                myView.findViewById<TextView>(R.id.timer_hour).setText("00")
                myView.findViewById<TextView>(R.id.timer_min).setText("00")
                myView.findViewById<TextView>(R.id.timer_sec).setText("00")
            }
        }

        // Inflate the layout for this fragment
        return myView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
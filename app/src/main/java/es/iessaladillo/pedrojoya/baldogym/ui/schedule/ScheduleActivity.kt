package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository.sessions
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel = ScheduleActivityViewModel(LocalRepository)

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().apply {

        setOnItemClickListener(object: ScheduleActivityAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {

                updateItem(getItem(position))

            }

        })

    }

    private fun updateItem(item: TrainingSession) {

        var list = viewModel.sessions.value

        if (item.userJoined) {

            item.userJoined = false

        }

        else {

            item.userJoined = true

        }

        list!!.forEach { if (it.id == item.id) it.userJoined = item.userJoined }

        updateList(list)

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.schedule_activity)

        setupViews()

        observe()

    }

    private fun observe() {

        viewModel.sessions.observe(this) { updateList(it) }

    }

    private fun updateList(it: List<TrainingSession>) {

        lstSessions.post {
            listAdapter.submitList(it)
            lblEmptyView.visibility =

                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
        }

    }

    private fun setupViews() {
        setupRecyclerView()
        mostrarDia()
    }

    private fun mostrarDia() {

        lblCurrentDay.text = getString(getCurrentWeekDay().nameResId)


        lblMonday.setOnClickListener{viewModel.navigateToDay(WeekDay.MONDAY)

            lblCurrentDay.text = getString(R.string.schedule_monday)
        }

        lblTuesday.setOnClickListener{viewModel.navigateToDay(WeekDay.TUESDAY)

            lblCurrentDay.text = getString(R.string.schedule_tuesday)

        }

        lblWednesday.setOnClickListener{viewModel.navigateToDay(WeekDay.WEDNESDAY)

            lblCurrentDay.text = getString(R.string.schedule_wednesday)

        }

        lblThursday.setOnClickListener{viewModel.navigateToDay(WeekDay.THURSDAY)

            lblCurrentDay.text = getString(R.string.schedule_thursday)
        }

        lblFriday.setOnClickListener{viewModel.navigateToDay(WeekDay.FRIDAY)

            lblCurrentDay.text = getString(R.string.schedule_friday)

        }

        lblSaturday.setOnClickListener{viewModel.navigateToDay(WeekDay.SATURDAY)

            lblCurrentDay.text = getString(R.string.schedule_saturday)

        }

        lblSunday.setOnClickListener{viewModel.navigateToDay(WeekDay.SUNDAY)

            lblCurrentDay.text = getString(R.string.schedule_sunday)

        }

    }

    private fun setupRecyclerView() {
        lstSessions.run {

            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            itemAnimator = DefaultItemAnimator()

            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

            adapter = listAdapter
        }

    }

}

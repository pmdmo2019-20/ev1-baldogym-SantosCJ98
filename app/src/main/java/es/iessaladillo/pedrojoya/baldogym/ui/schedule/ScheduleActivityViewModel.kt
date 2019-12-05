package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay

class ScheduleActivityViewModel(private val repository: Repository) {

    private val _sessions: MutableLiveData<List<TrainingSession>> = MutableLiveData()
    val sessions: LiveData<List<TrainingSession>>
        get() = _sessions

    init {

        _sessions.value = repository.navigateToDay(getCurrentWeekDay())

    }



    fun navigateToDay(weekDay: WeekDay) {

        val week = repository.navigateToDay(weekDay)

        _sessions.value = week

    }





}
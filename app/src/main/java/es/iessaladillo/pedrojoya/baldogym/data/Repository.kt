package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {

    fun navigateToDay(weekDay: WeekDay): List<TrainingSession>

    fun editSession(session: TrainingSession, isJoined: Boolean)

}
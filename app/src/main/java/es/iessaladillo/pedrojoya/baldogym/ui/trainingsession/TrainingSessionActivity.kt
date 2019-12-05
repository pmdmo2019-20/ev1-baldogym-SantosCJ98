package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityViewModel
import kotlinx.android.synthetic.main.training_session_activity.*

lateinit var session: TrainingSession

private val viewModel = ScheduleActivityViewModel(LocalRepository)

class TrainingSessionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.training_session_activity)

        setupViews()

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            var intentResult = Intent().putExtra("EXTRA_JOIN", !session.userJoined)
            setResult(1, intentResult)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun setupViews() {

        val extras = intent.extras

        session =  viewModel.querySession().find{it.id == extras.getLong("EXTRA_ID")} as TrainingSession

        imgSess.setImageResource(session.photoResId)

        lblName.text = session.name

        lblTr.text = session.trainer

        lblDia.text = session.weekDay.name

        lblHora.text = session.time

        lblHab.text = session.room

        lblDesc.text = session.description

        lblParticipants.text = session.participants.toString()

        if (!session.userJoined) {
            btnjoin.visibility = View.VISIBLE
            btnquit.visibility = View.INVISIBLE

        }

        else {

            btnjoin.visibility = View.INVISIBLE
            btnquit.visibility = View.VISIBLE


        }

        btnjoin.setOnClickListener{session.participants++

            lblParticipants.text = session.participants.toString()

            btnjoin.visibility = View.INVISIBLE
            btnquit.visibility = View.VISIBLE

        }

        btnquit.setOnClickListener{session.participants--

            lblParticipants.text = session.participants.toString()

            btnjoin.visibility = View.VISIBLE
            btnquit.visibility = View.INVISIBLE

        }


    }

}

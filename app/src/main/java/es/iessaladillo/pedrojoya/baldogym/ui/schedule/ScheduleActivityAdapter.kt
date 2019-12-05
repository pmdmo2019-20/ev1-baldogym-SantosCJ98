package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*


class ScheduleActivityAdapter : RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    init {

        setHasStableIds(true)

    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)

        return ViewHolder(itemView, onItemClickListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(data[position])

    }

    fun submitList(newList: List<TrainingSession>) {

        data = newList

        notifyDataSetChanged()



    }

    private var data: List<TrainingSession> = emptyList()

    init {

        setHasStableIds(true)

    }

    override fun getItemId(position: Int): Long {

        return data[position].id

    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {

        this.onItemClickListener = onItemClickListener

    }



    fun getItem(position: Int) = data[position]

    class ViewHolder(override val containerView: View, onItemClickListener: OnItemClickListener?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

            btnjoin.setOnClickListener {

                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {

                    onItemClickListener?.onItemClick(position)

                }

            }

            btnquit.setOnClickListener {

                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {

                    onItemClickListener?.onItemClick(position)

                }

            }

        }


        fun bind(session: TrainingSession) {


            lblTime.text = session.time

            imgSession.setImageResource(session.photoResId)

            lblSession.text = session.name

            lblTrainer.text = session.trainer

            lblRoom.text = session.room

            var participants = session.participants



            if (!session.userJoined) {

                lblParticipants.text = participants.toString()
                btnjoin.visibility = View.VISIBLE
                btnquit.visibility = View.INVISIBLE

            }

            else {

                participants++

                lblParticipants.text = participants.toString()

                btnjoin.visibility = View.INVISIBLE
                btnquit.visibility = View.VISIBLE


            }


        }

    }
}



package com.android.fundamentals.workshop03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.android.fundamentals.workshop03.solution.ActorsViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//TODO 2: make listener constructor parameter
class WS03ActorsAdapter: RecyclerView.Adapter<ActorsViewHolder>() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var actors = mutableListOf<Actor>()

    override fun getItemViewType(position: Int): Int {
        return when (actors.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_actors_empty, parent, false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_actors_data, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(imageOption, actors[position])
                //TODO 3: set onClick listener to binded view
            }
            is EmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors.toMutableList()
    }
}

abstract class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : ActorsViewHolder(itemView)
private class DataViewHolder(itemView: View) : ActorsViewHolder(itemView) {

    val avatar: ImageView? = itemView.findViewById(R.id.iv_actor_avatar)
    val name: TextView? = itemView.findViewById(R.id.tv_actor_name)
    val oscarState: TextView? = itemView.findViewById(R.id.tv_actor_oscar_state)

    fun onBind(options: RequestOptions, actor: Actor) {
        Glide.with(context)
            .load(actor.avatar)
            .apply(options)
            .into(avatar)

        name?.text = actor.name

        oscarState?.text = context.getString(
            R.string.fragment_actors_avatar_oscar_state_text,
            actor.hasOscar.toString()
        )
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_ACTORS = 1

/*TODO 1: create interface of clickListener with method
         fun onClick(actor: Actor)
*/
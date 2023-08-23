package com.snhustudent.ontime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snhustudent.ontime.R;
import com.snhustudent.ontime.interfaceListeners.OnItemClickListener;
import com.snhustudent.ontime.model.Event;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    OnItemClickListener onItemClickListener;
    List<Event> eEvents;
    Context context;

    public EventListAdapter(Context context, List<Event> eventList, OnItemClickListener listener) {
        this.context = context;
        this.eEvents = eventList;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_items, parent, false);
        return new EventListViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        holder.bind(eEvents.get(position));
    }

    @Override
    public int getItemCount() {
       return eEvents.size();
    }

    public Event getEventAtPosition(int position) {
        return eEvents.get(position);
    }

    public void setEvent(Event event) {
        eEvents.add(event);
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {
        private final TextView eNameView, eDateView, eTimeView, eLocationView, eDescriptionView;
        private final Button deleteButton;
        public EventListViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            eNameView = itemView.findViewById(R.id.event_name_view);
            eDateView = itemView.findViewById(R.id.event_date_view);
            eTimeView = itemView.findViewById(R.id.event_time_view);
            eLocationView = itemView.findViewById(R.id.event_location_view);
            eDescriptionView = itemView.findViewById(R.id.event_description_view);
            deleteButton = itemView.findViewById(R.id.button_delete);

            itemView.setOnClickListener(view -> listener.onItemClick(getBindingAdapterPosition()));

            itemView.setOnLongClickListener(view -> {
                listener.onItemLongClick(getBindingAdapterPosition());
                return true;
            });
        }
        private void bind(Event event) {
            eNameView.setText(event.getName());
            eDateView.setText(event.getDate());
            eTimeView.setText(event.getTime());
            eLocationView.setText(event.getLocation());
            eDescriptionView.setText(event.getDescription());
            deleteButton.setActivated(true);
        }
    }
}

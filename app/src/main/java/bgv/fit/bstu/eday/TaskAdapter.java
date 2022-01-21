package bgv.fit.bstu.eday;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import retrofit2.Callback;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder >{
    ArrayList<Task> tasks;
    Fragment context;

    interface OnStateClickListener{
        void onStateClick(Task task, int position);
    }
    private final OnStateClickListener onClickListener;

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder  holder, int position) {
        Task task = tasks.get(position);
        holder.name.setText(tasks.get(position).getName());
        holder.description.setText(tasks.get(position).getDescription());
        holder.datentime.setText(tasks.get(position).getDate()+" "+tasks.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(task, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public TaskAdapter(Fragment context, ArrayList<Task> tasks, OnStateClickListener onClickListener){
        this.context = context;
        this.tasks=tasks;
        this.onClickListener = onClickListener;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView name, description, datentime;
        public TaskViewHolder(View view) {
            super(new RecyclerContextMenuInfoWrapperView(view));
            ((RecyclerContextMenuInfoWrapperView)itemView).setHolder(this);
            context.registerForContextMenu(itemView);
            name=view.findViewById(R.id.tnamet);
            description=view.findViewById(R.id.tdescr);
            datentime=view.findViewById(R.id.tdatentime);
        }
    }
}

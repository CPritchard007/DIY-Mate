package ca.stclairconnect.pritchard.curtis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.stclairconnect.pritchard.curtis.Objects.Project;

public class ProjectListRecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    FragmentManager fm;
String[] names = {"Abra", "Cadabra", "Drowzy", "Charzard", "Picachu"};
//String[] projects = {"Tabletop Sim", "Tabletop Arcade", "DIY Console", "Build a Controller", "Pi Mirror"};
ArrayList<Project> projects;
    public ProjectListRecyclerAdapter(Context context, ArrayList<Project> projects){
       this.context = context;
       this.projects = projects;
       this.fm = fm;
   }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_view_list_item, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final Project project = projects.get(i);
        ((CustomViewHolder)viewHolder).circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move the user to the Profile of this user
                ProjectPageFragment.newInstance(project.getId());
            }
        });
        ((CustomViewHolder)viewHolder).projectTitle.setText(project.getName());
        ((CustomViewHolder)viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move the user to the applications project
            }
        });



    }

    @Override
    public int getItemCount() {
        if (projects.isEmpty())
            return 0;
        else
            return projects.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView projectTitle;
        TextView projectUser;
        ImageView circleImageView;
        CardView cardView;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.projectTitle);
            circleImageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.itemList);
        }

    }

}

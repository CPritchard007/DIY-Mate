package ca.stclairconnect.pritchard.curtis;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v4.app.FragmentActivity;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class ProjectListRecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    Activity activity;
ArrayList<Project> projects;
    public ProjectListRecyclerAdapter(Context context, ArrayList<Project> projects, Activity activity){
       this.context = context;
       this.projects = projects;
       this.activity= activity;
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
        Bitmap image = BitmapFactory.decodeFile(project.getImage());
        ((CustomViewHolder)viewHolder).circleImageView.setImageBitmap(image);
        ((CustomViewHolder)viewHolder).projectTitle.setText(project.getName());
        ((CustomViewHolder)viewHolder).projectDescription.setText(project.getDescription());
        ((CustomViewHolder)viewHolder).content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content, ProjectPageFragment.newInstance(project.getId())).addToBackStack(null).commit();
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
        TextView projectDescription;
        CircleImageView circleImageView;
        ConstraintLayout content;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.projectTitle);
            projectDescription = itemView.findViewById(R.id.projectDescription);
            circleImageView = itemView.findViewById(R.id.imageView);
            content = itemView.findViewById(R.id.listItem);
        }

    }

}

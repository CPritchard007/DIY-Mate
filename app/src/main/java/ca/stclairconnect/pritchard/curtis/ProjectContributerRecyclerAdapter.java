package ca.stclairconnect.pritchard.curtis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ca.stclairconnect.pritchard.curtis.Objects.Contributor;
import ca.stclairconnect.pritchard.curtis.Objects.Project;

public class ProjectContributerRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
   Project project;
    ArrayList<Contributor> contributors;
    public ProjectContributerRecyclerAdapter(Context context, Project project){
        this.context = context;
        this.project = project;
        DatabaseHelper db = new DatabaseHelper(context);
        contributors = db.getContributorByProjectId(project.getId());

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.contributor_list_layout,viewGroup,false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Contributor contributor = contributors.get(i);
      //  ((CustomViewHolder)viewHolder).image.setImageResource(R.drawable.ic_launcher_round);
        ((CustomViewHolder)viewHolder).image.setImageResource(contributor.getImage());
        ((CustomViewHolder)viewHolder).name.setText(contributor.getName());
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.contributorImage);
            name = itemView.findViewById(R.id.contributorName);


        }
    }
}

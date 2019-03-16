package ca.stclairconnect.pritchard.curtis;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProjectListRecyclerAdapter extends RecyclerView.Adapter {

    Context context;
String[] names = {"Abra", "Cadabra", "Drowzy", "Charzard", "Picachu"};
String[] projects = {"Tabletop Sim", "Tabletop Arcade", "DIY Console", "Build a Controller", "Pi Mirror"};

    public ProjectListRecyclerAdapter(Context context){
       this.context = context;
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
        ((CustomViewHolder)viewHolder).circleImageView.setImageResource(R.drawable.profile_image);
        ((CustomViewHolder)viewHolder).circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move the user to the Profile of this user

            }
        });
        ((CustomViewHolder)viewHolder).projectUser.setText(names[(int)(Math.random()*names.length)]);
        ((CustomViewHolder)viewHolder).projectTitle.setText(projects[(int)(Math.random()*projects.length)]);
        ((CustomViewHolder)viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move the user to the applications project
                ProjectPageFragment.newInstance("","");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView projectTitle;
        TextView projectUser;
        CircleImageView circleImageView;
        CardView cardView;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitle = itemView.findViewById(R.id.projectTitle);
            projectUser = itemView.findViewById(R.id.projectUser);
            circleImageView = itemView.findViewById(R.id.circleImageView);
            cardView = itemView.findViewById(R.id.content);
        }

    }

}

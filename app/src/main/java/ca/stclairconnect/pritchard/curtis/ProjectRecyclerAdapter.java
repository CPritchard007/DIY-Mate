package ca.stclairconnect.pritchard.curtis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.stclairconnect.pritchard.curtis.Objects.ListItem;
import ca.stclairconnect.pritchard.curtis.Objects.Project;


public class ProjectRecyclerAdapter extends RecyclerView.Adapter {
        private Context context;
        private ArrayList<ListItem> listItems;
    public ProjectRecyclerAdapter(Context context, Project project){
        this.context = context;
        DatabaseHelper db = new DatabaseHelper(context);
        this.listItems = db.getListItemsByProjectId(project.getId());
        System.out.println("THERE ARE "+ db.getListItemsByProjectId(project.getId()).size() + " items in this list");

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_layout,viewGroup,false);


        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);
        ((CustomViewHolder)viewHolder).itemName.setText(listItem.getName());
        ((CustomViewHolder)viewHolder).itemImage.setImageResource(R.drawable.checkmark);
        ((CustomViewHolder)viewHolder).casing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItem.setActive(!listItem.isActive());
                if (listItem.isActive())
                    ((CustomViewHolder)viewHolder).itemImage.setVisibility(View.VISIBLE);
                else
                    ((CustomViewHolder)viewHolder).itemImage.setVisibility(View.INVISIBLE);
            }
        });

        if (listItem.isActive())
            ((CustomViewHolder)viewHolder).itemImage.setVisibility(View.VISIBLE);
        else
            ((CustomViewHolder)viewHolder).itemImage.setVisibility(View.INVISIBLE);



        ((CustomViewHolder)viewHolder).casing.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Delete " + listItem.getName() + "?");
                alertDialog.setMessage("This will remove the item from your list");

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems == null?0:listItems.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView itemName;
        protected ImageView itemImage;
        protected ConstraintLayout casing;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.listItemText);
            itemImage = itemView.findViewById(R.id.checkmark);
            casing = itemView.findViewById(R.id.casing);



        }
    }
}


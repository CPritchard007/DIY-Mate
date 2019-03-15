package ca.stclairconnect.pritchard.curtis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


public class ProjectRecyclerAdapter extends RecyclerView.Adapter {
        private Context context;
    public ProjectRecyclerAdapter(Context context){
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_layout,viewGroup,false);


        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((CustomViewHolder)viewHolder).checkBox.setText("default");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
class CustomViewHolder extends RecyclerView.ViewHolder{
    CheckBox checkBox;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}

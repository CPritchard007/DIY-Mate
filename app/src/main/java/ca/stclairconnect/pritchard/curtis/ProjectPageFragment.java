package ca.stclairconnect.pritchard.curtis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ca.stclairconnect.pritchard.curtis.Objects.Contributor;
import ca.stclairconnect.pritchard.curtis.Objects.ListItem;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProjectPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectPageFragment newInstance(String param1, String param2) {
        ProjectPageFragment fragment = new ProjectPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_project_page, container, false);
        MainActivity.navigation.setVisibility(View.VISIBLE);

        final RecyclerView recyclerView = view.findViewById(R.id.itemList);


        final ArrayList<ListItem> listItems = new ArrayList<ListItem>();
    final ArrayList<Contributor> contributors = new ArrayList<Contributor>();


        listItems.add(new ListItem("This is the name",true));
        contributors.add(new Contributor("Darrec","his name is Darrec", R.drawable.ic_launcher_round,"healer"));
        DatabaseHelper db = new DatabaseHelper(getContext());
        final ProjectRecyclerAdapter[] adapter = {new ProjectRecyclerAdapter(getContext(), listItems)};
        recyclerView.setAdapter(adapter[0]);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ConstraintLayout alertLayer = view.findViewById(R.id.alertLayer);



        final ConstraintLayout contributorLayout = view.findViewById(R.id.contributorLayer);

        final EditText contributorName = view.findViewById(R.id.contributorName);
        final EditText contributorPosition = view.findViewById(R.id.contributorPosition);

        TextView AddButton  = view.findViewById(R.id.add_list);

        final TextView errorList = view.findViewById(R.id.errorText_items);
        final TextView errorContributors = view.findViewById(R.id.errorText_contributors);
        errorContributors.setVisibility(View.GONE);
        errorList.setVisibility(View.GONE);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertLayer.setVisibility(View.VISIBLE);
            }
        });

        alertLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertLayer.setVisibility(View.GONE);
            }
        });

        final EditText itemName = view.findViewById(R.id.itemName);

        final CheckBox itemIsComplete = view.findViewById(R.id.itemActivity);

        Button listItemSubmit = view.findViewById(R.id.listItemSubmit);
        if(itemName.getText()+"" != "") {
            listItemSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    errorList.setVisibility(View.GONE);
                    listItems.add(new ListItem(itemName.getText() + "", itemIsComplete.isChecked()));

                    itemName.setText("");
                    itemIsComplete.setChecked(false);

                    alertLayer.setVisibility(View.INVISIBLE);
                    System.out.println(itemIsComplete.isChecked());
                    adapter[0] = new ProjectRecyclerAdapter(getContext(), listItems);
                    recyclerView.setAdapter(adapter[0]);

                }
            });
        }else{
            errorList.setVisibility(View.VISIBLE);
        }



        final RecyclerView contributorView = view.findViewById(R.id.contributor);
        ProjectContributerRecyclerAdapter adapter1 = new ProjectContributerRecyclerAdapter(getContext(), contributors);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        contributorView.setLayoutManager(manager);

        contributorView.setAdapter(adapter1);




        TextView addContributor = view.findViewById(R.id.add_contributers);



        addContributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contributorLayout.setVisibility(View.VISIBLE);

            }
        });
        contributorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contributorLayout.setVisibility(View.GONE);
            }
        });



        if (contributorName.getText()+"" != "" && contributorPosition.getText()+"" != "") {
            addContributor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    errorContributors.setVisibility(View.GONE);
                    contributors.add(new Contributor(contributorName.getText()+"","",R.drawable.ic_launcher_round,contributorPosition.getText()+""));
                    contributorView.setAdapter(new ProjectContributerRecyclerAdapter(getContext(),contributors));
                    contributorName.setText("");
                    contributorPosition.setText("");
                }
            });
        }else{
            errorContributors.setVisibility(View.VISIBLE);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

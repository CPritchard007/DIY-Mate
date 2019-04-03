package ca.stclairconnect.pritchard.curtis;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.stclairconnect.pritchard.curtis.Objects.ListItem;
import ca.stclairconnect.pritchard.curtis.Objects.Profile;
import ca.stclairconnect.pritchard.curtis.Objects.Tag;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String imageLocation;

    public static final int CAMERA_PERMISSION_LABEL = 1;
    public static final int CAMERA_INTENT_LABEL = 2;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProfileFragment newInstance(String param1, String param2) {
        AddProfileFragment fragment = new AddProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_profile, container, false);
        MainActivity.navigation.setVisibility(View.INVISIBLE);
        final List<String> TagList = new ArrayList();
        final EditText username = view.findViewById(R.id.edit_user);
        final EditText description = view.findViewById(R.id.description);
        final EditText tagInput = view.findViewById(R.id.tagText);
        final CircleImageView circleImageView = view.findViewById(R.id.edit_profile);
        Button tagSubmit = view.findViewById(R.id.tagSubmit);
        final ListView listView = view.findViewById(R.id.tagList);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tagSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = tagInput.getText() + "";
                TagList.add(tag);
                tagInput.setText("");
            }
        });




        Button submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = null;
                String desc = null;
                DatabaseHelper db = new DatabaseHelper(getContext());
                if (username.getText()+"" != ""){
                    user = ""+username.getText();

                    if ( description.getText()+"" != "") {
                    desc = "" + description.getText();

                    Profile newProfile = new Profile(user, R.drawable.profile_image, desc,true);
                    int userId = db.addProfile(newProfile);
                    newProfile.setId(userId);
                    MainActivity.currentUser = newProfile;
                    System.out.println("[SOUT]=> ADDED CURRENT USER: " + MainActivity.currentUser.getName());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new ProjectsListFragment()).addToBackStack(null).commit();
                        for (int i = 0; i < TagList.size(); i++) {
                            int tagId = db.addTag(new Tag(TagList.get(i)), MainActivity.currentUser);
                            db.profileTagForeignKey(newProfile.getId(), tagId);
                        }
                    }
                }
            }
        });



        listView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,TagList));


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

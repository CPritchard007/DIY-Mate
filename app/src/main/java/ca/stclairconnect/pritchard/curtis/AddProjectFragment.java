package ca.stclairconnect.pritchard.curtis;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ca.stclairconnect.pritchard.curtis.Objects.Picture;
import ca.stclairconnect.pritchard.curtis.Objects.Project;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProjectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProjectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private static final int CAMERA_INTENT_LABEL = 1;
    private static final int CAMERA_PERMISSION_LABEL = 2;
    private String imageLocation;
    private int imageId;



    public AddProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProjectFragment newInstance(String param1, String param2) {
        AddProjectFragment fragment = new AddProjectFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_project, container, false);
        MainActivity.navigation.setVisibility(View.VISIBLE);

        final CircleImageView projectImageView = view.findViewById(R.id.projectImage);
        final EditText name = view.findViewById(R.id.name);
        final EditText desc = view.findViewById(R.id.desc);
        final EditText tag = view.findViewById(R.id.tags);
        Button addTag = view.findViewById(R.id.add_tag);
        Button submit = view.findViewById(R.id.submit);
        final ListView list = view.findViewById(R.id.listView);
        final ArrayList<String> tags = new ArrayList<>();
        list.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,tags));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                db.addProject(new Project(name.getText()+"", new Picture(imageLocation).getId() ,desc.getText()+""));
            }
        });

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag.getText()+"" != "") {
                    tags.add(tag.getText()+"");
                }
                list.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, tags));
            }
        });


        projectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("Camera Permission");
                        alertDialog.setMessage("We need access to your camera settings to add an image");
                        alertDialog.setButton("allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                ;
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_LABEL);

                            }
                        });
                        alertDialog.show();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_LABEL);
                    }
                }else{
                    File picture = null;
                    try {
                        picture = createTempImageLocation();

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(),"ca.stclairconnect.pritchard.curtis.FileProvider",picture));
                    if (i.resolveActivity(getActivity().getPackageManager()) != null){
                        startActivityForResult(i, CAMERA_INTENT_LABEL);
                    }
                }
            }

        });
        return view;
    }

    File createTempImageLocation() throws IOException{
        //Create the name of the image
        String fileName = "DIY-Mate-app_" + System.currentTimeMillis();
        //Grab the directory we want to save the image
        File directory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //Create the image in that directory
        File image =  File.createTempFile(fileName, ".jpg", directory);
        //Save the location of the image
        imageLocation = image.getAbsolutePath();
        return image;
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check who sent us a result and what the status of that result
        if(requestCode == CAMERA_INTENT_LABEL && resultCode == RESULT_OK){
            //Decode the image from where it is located on the phone
            Bitmap image = BitmapFactory.decodeFile(imageLocation);
            //Dynamically build an ImageView of the image and display it on the screen
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(image);


            DatabaseHelper db = new DatabaseHelper(getContext());
            //Add the picture to the DB
            //Grab the location of the picture in the DB
            int picID = db.addPicture(new Picture(imageLocation));
            if(picID != -1){
                imageId = picID;

            }
        }
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

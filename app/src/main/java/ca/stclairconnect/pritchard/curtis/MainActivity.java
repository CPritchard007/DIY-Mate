package ca.stclairconnect.pritchard.curtis;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ProjectPageFragment.OnFragmentInteractionListener, ProjectsListFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

 public FragmentManager fm = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                //use the same Recycler page for both the global and local list
                case R.id.navigation_project_local:
                    fm.beginTransaction().replace(R.id.content, new ProjectsListFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_insert:
                    return true;
                case R.id.navigation_profile:
                    fm.beginTransaction().replace(R.id.content, new ProfileFragment()).addToBackStack(null).commit();
                    return true;
            }
            return false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().replace(R.id.content, new ProjectPageFragment()).addToBackStack(null).commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

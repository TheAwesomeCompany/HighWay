package click.theawesome.mda.yourmechanic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import click.theawesome.mda.yourmechanic.R;
import click.theawesome.mda.yourmechanic.ui.adapter.HighwayAdapter;
import click.theawesome.mda.yourmechanic.ui.model.Model;
import click.theawesome.mda.yourmechanic.ui.util.DebugHighwayUtils;

public class HighwayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final String TAG = HighwayActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private List<Model> mModels;
    private HighwayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highway);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mModels = DebugHighwayUtils.generateModels(15);

        Comparator<Model> comparator = new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return (int) (o1.getTime() - o2.getTime());
            }
        };
        HighwayAdapter.Listener listener = new HighwayAdapter.Listener() {
            @Override
            public void onItemClicked(View view, Model model) {
                Log.d(TAG, model.getName());
                Intent intent = new Intent(HighwayActivity.this, DetailActivity.class);
                // Pass data object in the bundle and populate details activity.
                intent.putExtra(DetailActivity.EXTRA_ITEM, model);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(HighwayActivity.this, view.findViewById(R.id.item_icon), "profile");
                startActivity(intent, options.toBundle());
            }
        };
        mAdapter = new HighwayAdapter(this, comparator, listener);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.replaceAll(mModels);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        iniTab((TabLayout) findViewById(R.id.sliding_tabs));
    }

    private void iniTab(final TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("PICKING"));
        tabLayout.addTab(tabLayout.newTab().setText("DELIVERED"));
        tabLayout.addTab(tabLayout.newTab().setText("IN TRANSIT"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                CharSequence text = null;

                if(tabLayout.getSelectedTabPosition() != 0) {
                    text = tab.getText();
                }

                onFilterItems(text);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void onFilterItems(CharSequence text) {
        if(TextUtils.isEmpty(text)){
            mAdapter.replaceAll(mModels);
            return;
        }

        final List<Model> filteredModelList = new ArrayList<Model>();

        for (Model model : mModels) {
            if(model.getState().equals(text)){
                filteredModelList.add(model);
            }
        }

        mAdapter.replaceAll(filteredModelList);

        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

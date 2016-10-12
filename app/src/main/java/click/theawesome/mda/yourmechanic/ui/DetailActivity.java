package click.theawesome.mda.yourmechanic.ui;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import click.theawesome.mda.yourmechanic.R;
import click.theawesome.mda.yourmechanic.databinding.ActivityDetailBinding;
import click.theawesome.mda.yourmechanic.ui.model.Model;
import click.theawesome.mda.yourmechanic.ui.util.AssetsUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set paddingTop of toolbar to height of status bar.
            // Fixes statusbar covers toolbar issue
            toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Model model = getIntent().getExtras().getParcelable(EXTRA_ITEM);
        binding.setModel(model);

        AssetsUtils.loadImageFromAssets(binding.contentDetailRoot.foodImage, model.getUrl() );
        AssetsUtils.loadImageFromAssets(binding.contentDetailRoot.foodMapImage, "a2c448f7e581aa216de87dee951e1dd3.png" );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

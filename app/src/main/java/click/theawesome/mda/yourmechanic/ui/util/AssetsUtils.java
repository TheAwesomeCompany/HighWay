package click.theawesome.mda.yourmechanic.ui.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mda on 10/11/16.
 */

public class AssetsUtils {
    public static void loadImageFromAssets(ImageView view, String path){
        // load image
        try {
            // get input stream
            InputStream ims = view.getContext().getAssets().open(path);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            view.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
    }
}

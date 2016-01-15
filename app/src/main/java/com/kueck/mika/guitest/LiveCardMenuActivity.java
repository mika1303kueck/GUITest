package com.kueck.mika.guitest;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * A transparent {@link Activity} displaying a "Stop" options menu to remove the { LiveCard}.
 */
public class LiveCardMenuActivity extends Activity {

    private ImageView imageView_stop, imageView_go;
    private boolean signal_red = true;

    @Override
    protected void onStart() {
        super.onStart();

        imageView_stop = (ImageView)findViewById(R.id.imageView);
        imageView_go = (ImageView)findViewById(R.id.imageView2);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // Open the options menu right away.
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.live_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_stop:
                // Stop the service which will unpublish the live card.
                stopService(new Intent(this, LiveCardService.class));
                return true;
            case R.id.change_signals:
            changeSignal();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        // Nothing else to do, finish the Activity.
        finish();
    }

    private void changeSignal() {
        signal_red = !signal_red;
        if (signal_red) {
            imageView_go.setVisibility(View.INVISIBLE);
            imageView_stop.setVisibility(View.VISIBLE);
        } else {
            imageView_go.setVisibility(View.VISIBLE);
            imageView_stop.setVisibility(View.INVISIBLE);
        }
    }
}

package de.kriegergilde.switchtimer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private Button buttonGreen;
    private Button buttonRed;
    private Button buttonBlue;
    private long lastClick;
    private Status status = Status.TROEDELN;
    private long arbeitszeit = 0;
    private long troedelzeit = 0;
    private long pausezeit = 0;
    private ProgressBar progressBarArbeit;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarArbeit = (ProgressBar) findViewById(R.id.progressBarArbeit);

        buttonGreen = (Button) findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (lastClick != 0) {
                    long zeitdiff = System.currentTimeMillis() - lastClick;
                    switch (status) {
                        case ARBEIT:
                            arbeitszeit += zeitdiff;
                            break;
                        case TROEDELN:
                            troedelzeit += zeitdiff;
                            break;
                        case PAUSE:
                            pausezeit += zeitdiff;
                        default:
                            // TODO
                    }
                }
                status = Status.ARBEIT;
                lastClick = System.currentTimeMillis();
                refreshData();

                if ( arbeitszeit + troedelzeit != 0 ){
                progressBarArbeit.setProgress((int)((arbeitszeit / ((double)arbeitszeit + troedelzeit))*100 ) );
                 }
            }
        });

        buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (lastClick != 0) {
                    long zeitdiff = System.currentTimeMillis() - lastClick;
                    switch (status) {
                        case ARBEIT:
                            arbeitszeit += zeitdiff;
                            break;
                        case TROEDELN:
                            troedelzeit += zeitdiff;
                            break;
                        case PAUSE:
                            pausezeit += zeitdiff;
                        default:
                            // TODO
                    }
                }
                status = Status.TROEDELN;
                lastClick = System.currentTimeMillis();
                refreshData();
                if ( arbeitszeit + troedelzeit != 0 ){
                    progressBarArbeit.setProgress((int)((arbeitszeit / ((double)arbeitszeit + troedelzeit))*100 ) );
                }
            }
        });

        buttonBlue = (Button) findViewById(R.id.buttonBlue);
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (lastClick != 0) {
                    long zeitdiff = System.currentTimeMillis() - lastClick;
                    switch (status) {
                        case ARBEIT:
                            arbeitszeit += zeitdiff;
                            break;
                        case TROEDELN:
                            troedelzeit += zeitdiff;
                            break;
                        case PAUSE:
                            pausezeit += zeitdiff;
                        default:
                            // TODO
                    }
                }
                status = Status.PAUSE;
                //buttonBlue.set
                lastClick = System.currentTimeMillis();
                refreshData();
                if ( arbeitszeit + troedelzeit != 0 ){
                    progressBarArbeit.setProgress((int)((arbeitszeit / ((double)arbeitszeit + troedelzeit))*100 ) );
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void refreshData() {

        {
            TextView textViewGreen = (TextView) findViewById(R.id.textViewGreen);
            textViewGreen.setText(ermittleZeit(arbeitszeit));
        }
        {
            TextView textViewRed = (TextView) findViewById(R.id.textViewRed);
            textViewRed.setText(ermittleZeit(troedelzeit));
        }
        {
            TextView textViewBlue = (TextView) findViewById(R.id.textViewBlue);
            textViewBlue.setText(ermittleZeit(pausezeit));
        }

    }

    private String ermittleZeit(long millis) {
        long minuten = millis / 1000 / 60;
        long sekunden = millis / 1000 % 60;
        return minuten + ":" + sekunden;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://de.kriegergilde.switchtimer/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://de.kriegergilde.switchtimer/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

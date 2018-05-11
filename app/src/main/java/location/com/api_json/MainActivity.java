package location.com.api_json;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    String Json_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=(TextView) findViewById(R.id.textview);

    }
    public void text(View view){

        URL MovieURL = buildUrl();

        new NewsQuery().execute(MovieURL);
        LoadData(view);
    }

    private void LoadData(View view) {
    if(Json_String==null){
        Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG);
    }
else {
        Intent intent=new Intent(this,Display_listView.class);
        intent.putExtra("Json_data",Json_String);
        startActivity(intent);
    }
    }

    public URL buildUrl(){

        Uri builtUri = Uri.parse("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=5d33faee98b84a05ad628c04d6bdda38").buildUpon()
                        .build();

        URL url = null;


        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }
    class NewsQuery extends AsyncTask<URL,Void,String> {


        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String NewsSearchResults = null;
            try {
               NewsSearchResults = getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return NewsSearchResults;
        }


        @Override
        protected void onPostExecute(String NewsSearchResults) {
            if (NewsSearchResults != null && !NewsSearchResults.equals("")) {
            mTextView.setText(NewsSearchResults);
             Json_String=NewsSearchResults;
            }
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }




}

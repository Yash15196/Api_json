package location.com.api_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Display_listView extends AppCompatActivity {
String json_data;
JSONObject jsonObject;
JSONArray jsonArray;
News_Adapter newsAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_list_view);
        newsAdapter=new News_Adapter(this,R.layout.row_layout);
        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(newsAdapter);
        json_data= getIntent().getExtras().getString("Json_data");
        String title,author,description;
        try {
            jsonObject=new JSONObject(json_data);
            jsonArray=jsonObject.getJSONArray("articles");
            int count=0;
            while(count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                title= jo.getString("author");
                author =jo.getString("title");
                description=jo.getString("description");
                model model=new model(author, title, description);
                count++;
                newsAdapter.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

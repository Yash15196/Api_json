package location.com.api_json;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class News_Adapter extends ArrayAdapter {
    List list=new ArrayList();

    public News_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        modelHolder modelholder;
        View row_layout;
        row_layout=convertView;

        if(row_layout==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row_layout=layoutInflater.inflate(R.layout.row_layout,parent,false);
             modelholder=new modelHolder();
            modelholder.tx_author=(TextView) row_layout.findViewById(R.id.textView_author);

            modelholder.tx_title=(TextView)row_layout.findViewById(R.id.textView_title);
            modelholder.tx_description=(TextView)row_layout.findViewById(R.id.textView_description);
        }
        else {
modelholder=(modelHolder) row_layout.getTag();
        }
        model model=(model)this.getItem(position);
        if(model.getDescription()!=null&& model.getAuthor()!=null&&model.getTitle()!=null) {
            modelholder.tx_description.setText(model.getDescription());
            modelholder.tx_author.setText(model.getAuthor());
            modelholder.tx_title.setText(model.getTitle());
        }
        return row_layout;
    }
    static class modelHolder{
        TextView tx_title,tx_description,tx_author;
    }
}

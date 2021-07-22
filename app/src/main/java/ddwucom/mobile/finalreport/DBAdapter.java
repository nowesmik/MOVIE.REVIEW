package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DBAdapter extends CursorAdapter {

    private Context context;
    private int layout;
    Cursor cursor;
    private LayoutInflater layoutInflater;

    public DBAdapter(Context context, int layout, Cursor c) {
        super(context, c);
        this.context = context;
        this.layout = layout;
        this.cursor = c;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView image = view.findViewById(R.id.imageView);
        TextView name = view.findViewById(R.id.name);
        TextView actor = view.findViewById(R.id.actor);
        TextView director = view.findViewById(R.id.director);

        final int pos = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
        switch(pos){
            case 1:
                image.setImageResource(R.drawable.movie1);
                break;
            case 2:
                image.setImageResource(R.drawable.movie2);
                break;
            case 3:
                image.setImageResource(R.drawable.movie3);
                break;
            case 4:
                image.setImageResource(R.drawable.movie4);
                break;
            case 5:
                image.setImageResource(R.drawable.movie5);
                break;
            case 6:
                image.setImageResource(R.drawable.movie6);
                break;
            default:
                image.setImageResource(R.drawable.movie0);
        }

        name.setText(cursor.getString(cursor.getColumnIndex("name")));
        actor.setText(cursor.getString(cursor.getColumnIndex("actor")));
        director.setText(cursor.getString(cursor.getColumnIndex("director")));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = layoutInflater.inflate(layout, parent, false);

        return v;
    }

    public Cursor getCursor(){
        return cursor;
    }
}

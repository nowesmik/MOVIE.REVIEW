package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

        DBHelper helper = null;
        Cursor cursor = null;

        public DBManager(Context context){
            helper = new DBHelper(context);
        }

        public Cursor getAllMovie(){
            SQLiteDatabase db = helper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);

            return cursor;
        }

        public MovieInfo getPositionMovie(int position){
            MovieInfo movie = new MovieInfo();

            if(!cursor.moveToPosition(position)){
                return null;
            }

            int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
            switch(id){
                case 1:
                    movie.setImageId(R.drawable.movie1);
                    break;
                case 2:
                    movie.setImageId(R.drawable.movie2);
                    break;
                case 3:
                    movie.setImageId(R.drawable.movie3);
                    break;
                case 4:
                    movie.setImageId(R.drawable.movie4);
                    break;
                case 5:
                    movie.setImageId(R.drawable.movie5);
                    break;
                case 6:
                    movie.setImageId(R.drawable.movie6);
                    break;
                default:
                    movie.setImageId(R.drawable.movie0);
            }

            movie.setId(id);
            movie.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME)));
            movie.setActor(cursor.getString(cursor.getColumnIndex(DBHelper.COL_ACTOR)));
            movie.setDirector(cursor.getString(cursor.getColumnIndex(DBHelper.COL_DIRECTOR)));
            movie.setRate(cursor.getString(cursor.getColumnIndex(DBHelper.COL_RATE)));
            movie.setComment(cursor.getString(cursor.getColumnIndex(DBHelper.COL_COMMENT)));

            return movie;
        }

        public Cursor searchMovie(int spinnerPosition, String value){
            SQLiteDatabase db = helper.getReadableDatabase();

            String selection = null;
            String [] selectArgs = new String[]{value};

            switch(spinnerPosition){
                case 0:
                    selection = DBHelper.COL_NAME + "=?";
                    break;
                case 1:
                    selection = DBHelper.COL_ACTOR + "=?";
                    break;
                case 2:
                    selection = DBHelper.COL_DIRECTOR + "=?";
                    break;
            }

            cursor = db.query(DBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

            return cursor;
        }

        public void addMovie(MovieInfo movie){
            ContentValues row = new ContentValues();
            row.put(DBHelper.COL_NAME, movie.getName());
            row.put(DBHelper.COL_ACTOR, movie.getActor());
            row.put(DBHelper.COL_DIRECTOR, movie.getDirector());
            row.put(DBHelper.COL_RATE, movie.getRate());
            row.put(DBHelper.COL_COMMENT, movie.getComment());

            SQLiteDatabase db = helper.getWritableDatabase();

            db.insert(DBHelper.TABLE_NAME, null, row);

        }

        public void deleteMovie(int position){
            SQLiteDatabase db = helper.getWritableDatabase();
            cursor.moveToPosition(position);

            String whereClause = DBHelper.COL_ID +"=?";
            String [] whereArgs = new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID)))};

            db.delete(DBHelper.TABLE_NAME, whereClause, whereArgs);

        }

        public boolean updateMovie(MovieInfo movie) {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues row = new ContentValues();
            row.put(DBHelper.COL_NAME, movie.getName());
            row.put(DBHelper.COL_ACTOR, movie.getActor());
            row.put(DBHelper.COL_DIRECTOR, movie.getDirector());
            row.put(DBHelper.COL_RATE, movie.getRate());
            row.put(DBHelper.COL_COMMENT, movie.getComment());

            String whereClause = DBHelper.COL_ID + "=?";
            String[] whereArgs = new String[]{String.valueOf(movie.getId())};

            int result = db.update(DBHelper.TABLE_NAME, row, whereClause, whereArgs);

            helper.close();

            if (result > 0)
                return true;

            return false;
        }
}

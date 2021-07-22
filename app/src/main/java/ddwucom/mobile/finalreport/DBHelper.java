package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "DBHelper";
    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_ACTOR = "actor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_RATE = "rate";
    public final static String COL_COMMENT = "comment";

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_NAME + " TEXT, " + COL_ACTOR + " TEXT, " + COL_DIRECTOR + " TEXT, " +
                COL_RATE + " TEXT, " + COL_COMMENT + " TEXT);";

        Log.d(TAG, sql);
        db.execSQL(sql);
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'크루엘라', '엠마 스톤', '크레이그 질레스피', '8', '역대 디즈니 빌런 영화 중 최고의 역작');" );
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'조제', '한지민, 남주혁', '김종관', '6', '영상미가 뛰어난 잔잔한 영화');" );
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'플립', '캘런 맥오리피', '로브 라이너', '10', '예쁜 동화같은 첫사랑 영화');" );
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'Aladdin', '윌 스미스', '가이 리치', '9', '실사화를 완벽하게 한 환상적인 영화');" );
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'메이즈 러너', '딜런 오브라이언', '웨스 볼', '10', '보는 내내 긴장을 놓칠 수 없는 영화');" );
        db.execSQL("insert into " + TABLE_NAME + " values( null, " + "'La La Land', '라이언 고슬링', '데이미언 셔젤', '7', '꿈과 성공, 사랑을 아름답게 표현한 영화');" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

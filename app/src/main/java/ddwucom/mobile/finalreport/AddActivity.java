package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_actor;
    EditText et_director;
    EditText et_rate;
    EditText et_comment;

    DBManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        manager = new DBManager(this);

        et_name = findViewById(R.id.et_add_name);
        et_actor = findViewById(R.id.et_add_actor);
        et_director = findViewById(R.id.et_add_director);
        et_rate = findViewById(R.id.et_add_rate);
        et_comment = findViewById(R.id.et_add_comment);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_add:
                MovieInfo movie = new MovieInfo();
                movie.setName(et_name.getText().toString());
                movie.setActor(et_actor.getText().toString());
                movie.setDirector(et_director.getText().toString());
                movie.setRate(et_rate.getText().toString());
                movie.setComment(et_comment.getText().toString());

                manager.addMovie(movie);
                Toast.makeText(this, "영화가 추가되었습니다", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                break;
            case R.id.btn_cancel:
                Toast.makeText(this, "추가 취소", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}

package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    int position;
    ImageView imageView;
    EditText et_name;
    EditText et_actor;
    EditText et_director;
    EditText et_rate;
    EditText et_comment;

    TextView tv_name;
    TextView tv_actor;
    TextView tv_director;
    TextView tv_rate;
    TextView tv_comment;

    MovieInfo movie;
    Button btn_ok;
    Button btn_home;

    DBManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        manager = new DBManager(this);

        final Intent intent = getIntent();
        movie = (MovieInfo) intent.getSerializableExtra("movie");
        position = intent.getIntExtra("position", 0);

        imageView = findViewById(R.id.update_imageView);
        et_name = findViewById(R.id.et_add_name);
        et_actor = findViewById(R.id.et_update_actor);
        et_director = findViewById(R.id.et_update_director);
        et_rate = findViewById(R.id.et_update_rate);
        et_comment = findViewById(R.id.et_update_comment);

        tv_name = findViewById(R.id.tv_update_name);
        tv_actor = findViewById(R.id.tv_update_actor);
        tv_director = findViewById(R.id.tv_update_director);
        tv_rate = findViewById(R.id.tv_update_rate);
        tv_comment = findViewById(R.id.tv_update_comment);

        imageView.setImageResource(movie.getImageId());
        et_name.setHint(movie.getName());
        et_actor.setHint(movie.getActor());
        et_director.setHint(movie.getDirector());
        et_rate.setHint(movie.getRate());
        et_comment.setText(movie.getComment());

        tv_name.setText(movie.getName());
        tv_actor.setText(movie.getActor());
        tv_director.setText(movie.getDirector());
        tv_rate.setText(movie.getRate());
        tv_comment.setText("'" + movie.getComment() + "'");

        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btn_ok = findViewById(R.id.btn_update_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie.setName(et_name.getText().toString());
                movie.setActor(et_actor.getText().toString());
                movie.setDirector(et_director.getText().toString());
                movie.setRate(et_rate.getText().toString());
                movie.setComment(et_comment.getText().toString());

                if(movie.getName().equals("")|| movie.getActor().equals("") || movie.getDirector().equals("")|| movie.getRate().equals("")|| movie.getComment().equals("")){
                    Toast.makeText(UpdateActivity.this, "내용을 빠짐없이 입력하세요", Toast.LENGTH_SHORT).show();
                }else {
                    if(manager.updateMovie(movie)) {
                        Toast.makeText(UpdateActivity.this, "업데이트 되었습니다", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                    }
                    else {
                        Toast.makeText(UpdateActivity.this, "error : 업데이트 실패", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
            }
        });
    }
}

package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

//과제명:영화 정보 관리 앱
//분반: 02분반
//학번: 20190946 성명: 김세원
//제출일 : 2021년 6월 23일
public class MainActivity extends AppCompatActivity {

    DBManager manager;
    DBAdapter dbAdapter;
    Cursor cursor;
    int spinnerPosition;
    Spinner spinner;
    GridView gridView;
    Intent intent;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DBManager(this);
        cursor = manager.getAllMovie();
        gridView = findViewById(R.id.gridView);
        dbAdapter = new DBAdapter(this, R.layout.view, cursor);
        gridView.setAdapter(dbAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("position", pos);
                intent.putExtra("movie", manager.getPositionMovie(position));
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(manager.getPositionMovie(position).getName() + "(을)를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                manager.deleteMovie(pos);
                                cursor = manager.getAllMovie();
                                dbAdapter.changeCursor(cursor);

                                Toast.makeText(MainActivity.this, "영화가 삭제되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "삭제 취소", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
                spinner.setSelection(spinnerPosition);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerPosition = 0;
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                cursor = manager.searchMovie(spinnerPosition, query);
                dbAdapter.changeCursor(cursor);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    cursor = manager.getAllMovie();
                    dbAdapter.changeCursor(cursor);
                }
                return true;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        cursor = manager.getAllMovie();
        dbAdapter.changeCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_add:
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.item_introduce:
                final ConstraintLayout introduceLayout = (ConstraintLayout) View.inflate(this, R.layout.introduce, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(introduceLayout)
                        .setPositiveButton("닫기", null)
                        .setCancelable(false)
                        .show();
                break;
            case R.id.item_finish:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "종료 취소", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
        return true;
    }
}
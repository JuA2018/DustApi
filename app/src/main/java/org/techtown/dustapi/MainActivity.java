package org.techtown.dustapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.dustapi.data.Dust;
import org.techtown.dustapi.data.Dustlist;
import org.techtown.dustapi.service.QueryService;
import org.techtown.dustapi.util.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView time;
    private TextView dust10;
    private TextView dust25;

    private final static double VER = 1.3;

    Button areadensity;
    Spinner spinner;
    Intent intent;
    String areaname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        dust10 = findViewById(R.id.dust10);
        dust25 = findViewById(R.id.dust25);

        spinner = findViewById(R.id.areaitem);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_item);
        //android.R.layout.simple_spinner_item -> 스피너에 대한 기본 모양을 지정해줌
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner의 선택항목 표시해 줌
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaname = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        areadensity = (Button) findViewById(R.id.density);
        areadensity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), areaname, Toast.LENGTH_SHORT).show();
                dust();
            }
        });
    }

    //값 받아와서 쓰기
    void dust() {
        QueryService queryService = RetrofitUtil.createService(QueryService.class);
        Call<Dust> call = queryService.getdust(areaname, 1, 10, VER);
        call.enqueue(new Callback<Dust>() {
            @Override
            public void onResponse(Call<Dust> call, Response<Dust> response) { //연결 성공
                //response.body는 내가 GET한값이 들어있음
                //그래서 밑에 처럼 하면 값을 받아올 수 있음
                Dust dust = response.body();
                //DustList에 저장
                List<Dustlist> dustlist = dust.getList();
                time.setText(dustlist.get(0).getDataTime());
                dust10.setText(dustlist.get(0).getPm10Value());
                dust25.setText(dustlist.get(0).getPm25Value());
            }

            @Override
            public void onFailure(Call<Dust> call, Throwable t) { //연결 실패
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}

package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView time;
Button minus , plus , reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        reset = findViewById(R.id.reset);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dec();

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inc();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

            }
        });


        getData();
    }




    int tyme = 0 ;
    private void inc() {

        tyme = tyme+1;
        if(tyme<= 9)
        time.setText("0" .concat(String.valueOf(tyme)));
        else
            time.setText(String.valueOf(tyme));
    }
    private void dec() {
        tyme = tyme-1;
        if(tyme<= 9)
            time.setText("0" .concat(String.valueOf(tyme)));
        else
            time.setText(String.valueOf(tyme));
        if (tyme<0){
            time.setText("00");
            tyme=0;
        Toast.makeText(MainActivity.this,
                "Counter not count negative value ....",
                Toast.LENGTH_SHORT).show();
    }
    }
    private void reset() {
        tyme = 0;
        time.setText("00");
    }
    public void savedata(){
      SharedPreferences savecount = getSharedPreferences("count",MODE_PRIVATE);
      SharedPreferences.Editor editor = savecount.edit();
      editor.putInt("key",tyme);
      editor.apply();

  }
    public  void getData(){
      SharedPreferences getcount = getSharedPreferences("count",MODE_PRIVATE);

      tyme = getcount.getInt("key",tyme);
      time.setText(String.valueOf(tyme));
  }

    @Override
    protected void onPause() {
        super.onPause();
        savedata();

    }
}
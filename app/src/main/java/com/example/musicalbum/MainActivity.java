package com.example.musicalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("노래 목록");  // 액티비티 이름 수정
    }

    public void play(View v) { //  오디오 아이템 터치시 호출되는 메소드
        int id = v.getId();  // 터치한 뷰의 id 인식
        LinearLayout layout = (LinearLayout) findViewById(id); // id에 해당되는 LinearLayout 객체 인식
        String tag = (String) layout.getTag();
        Intent it = new Intent(this, AudioImage.class); // 호출한 클래스에 대한 Intent 객체 생성
        it.putExtra("it_tag", tag); // 인텐트 변수에 tag값 저장
        startActivity(it); // 인텐트 실행
    }
}
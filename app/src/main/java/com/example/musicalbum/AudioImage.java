package com.example.musicalbum;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class AudioImage extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer(); // 노래 재생을 위히여 MediaPlayer 객체 필요


    // 이 액티비티 실행을 요청한 Intent를 취득해 전송된 Tag 정보를 획득 : tag
    // 출력이 필요로하는 위젯 인식하여 저장 : tile / song_image / lyrics
    // 어떤 노래가 선택되었는지 파악해 (Tag 정보) 적절한 내용을 여기에 출력하고 해당 오디오 파일을 파악하여 노래를 재생
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_image);

        int stringId;
        String mkKey;

        setTitle("노래 저장"); // 액티비티 이름 설정

        Intent it = getIntent();   // 호출한 Intent 객체 획득
        String tag = it.getStringExtra("it_tag"); // tag값 추출

        TextView title = findViewById(R.id.title);
        ImageView song_image = findViewById(R.id.song_image);
        TextView lyrics  = findViewById(R.id.lyrics);
        // [TextView], [ImageView] 객체 획득

        Resources res = getResources(); // [Resource] 객체 획득

        stringId = res.getIdentifier("title" + tag, "string", getPackageName());
        mkKey = res.getString(stringId);
        title.setText(mkKey);

        stringId = res.getIdentifier("song_image" + tag, "string", getPackageName());
        mkKey = res.getString(stringId);    // 이미지 파일 이름을 파악
        int imageld = res.getIdentifier(mkKey, "drawable", getPackageName());  // 이미지 파일 이름에 대응되는 ID를 파악
        song_image.setImageResource(imageld);  // drawable 폴더에서 [imageld]에 해당되는 이미지를 출력

        stringId = res.getIdentifier("lyrics" + tag, "string", getPackageName());
        mkKey = res.getString(stringId);
        title.setText(mkKey);

        stringId = res.getIdentifier("audio" + tag, "string", getPackageName());  // [string.xml] 에서 "audio" + tag 속성의 id 획득
        mkKey = res.getString(stringId);    // [id]에 해당하는 속성값(파일명) 추출
        int audiold = res.getIdentifier(mkKey, "raw", getPackageName()); //[raw]에서 오디오 파일 id 획득
        mp = MediaPlayer.create(this, audiold);
        mp.setLooping(false); // 오디오 파일을 미디어 플레이어에 설정
        mp.start();

    }

    public void goBack(View v) { // 화면을 터치하면 호출되는 콜백 메소드 구현
        // 노래 채생중이면 중지하고 MediaPlayer 자원을 해제하고 액티비티 종료

        if(mp.isPlaying()){  // 노래 재생중이면 중지하고 자원 해제
            mp.stop();
            mp.release();
        }
        finish();  // 엑티비티 종료
    }
}

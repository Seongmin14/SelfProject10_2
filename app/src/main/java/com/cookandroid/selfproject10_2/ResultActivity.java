package com.cookandroid.selfproject10_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        // 인텐트에서 데이터 받기
        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        // 이미지 리소스 배열 정의
        Integer[] imageFileId = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        };

        // 최다 득표 이미지 찾기 및 표시
        TextView textTitle = findViewById(R.id.textTitle);
        ImageView imageResult = findViewById(R.id.imageResult);

        int maxVoteIndex = 0;
        if (voteResult != null && voteResult.length > 0) {
            for (int i = 1; i < voteResult.length; i++) {
                if (voteResult[maxVoteIndex] < voteResult[i]) {
                    maxVoteIndex = i;
                }
            }
            if (imageName != null && maxVoteIndex < imageName.length) {
                textTitle.setText(imageName[maxVoteIndex]);
            }
            if (maxVoteIndex < imageFileId.length) {
                imageResult.setImageResource(imageFileId[maxVoteIndex]);
            }
        }

        // RatingBar 배열 초기화 및 설정
        RatingBar[] ratingBars = new RatingBar[]{
                findViewById(R.id.ratingBar1),
                findViewById(R.id.ratingBar2),
                findViewById(R.id.ratingBar3),
                findViewById(R.id.ratingBar4),
                findViewById(R.id.ratingBar5),
                findViewById(R.id.ratingBar6)
        };

        // 투표 결과 RatingBar에 반영
        if (voteResult != null) {
            for (int i = 0; i < ratingBars.length && i < voteResult.length; i++) {
                if (ratingBars[i] != null) {
                    ratingBars[i].setRating(voteResult[i]);
                }
            }
        }

        // 돌아가기 버튼 설정
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> finish());
    }
}
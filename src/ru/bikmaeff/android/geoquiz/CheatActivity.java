package ru.bikmaeff.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = "ru.bikmaeff.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "ru.bikmaeff.android.geoquiz.answer_shown";
	private static final String TAG = "CheatActivity";
		
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	//private boolean mCheater;
	private boolean mIsAnswerShown;
	
	private void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called CheatActivity");
		setContentView(R.layout.activity_cheat);
		
   
		if (null != savedInstanceState){
			mIsAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
			setAnswerShownResult(mIsAnswerShown);
		}
	
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View v) {
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				mIsAnswerShown = true;
				setAnswerShownResult(mIsAnswerShown);
			}
		});
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN,mIsAnswerShown);
	}

}

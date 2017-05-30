package com.asuscomm.yangyinetwork.bitenpeach.ui.main.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.asuscomm.yangyinetwork.bitenpeach.R;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.dummy.DummyData;
import com.asuscomm.yangyinetwork.bitenpeach.ui.main.activity.adapter.RawTextAdapter;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;
import com.asuscomm.yangyinetwork.bitenpeach.utils.witai.WitaiService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.bitenpeach.models.logic.RawTextProcesser.processRawText;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private final String refName = "rawTexts";

    private RecyclerView mRawTextListView;
    private RawTextAdapter mRawTextAdapter;
    private ProgressBar mProgressBar;
    private EditText mRawTextEditText;
    private Button mSendButton;
    
    private DatabaseReference mRawTextsDatabaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d(TAG, "onCreate: ");
        AppController.getInstance().chkPermission(Manifest.permission.RECEIVE_SMS, this);
        AppController.getInstance().chkPermission(Manifest.permission.SEND_SMS, this);
        initFirebaseDatabase();
        initView();

        testWitai();
    }

    private void testWitai() {
//        List<RawText> rawTexts = DummyData.getDummyRawTexts();
//        for (RawText each:
//             rawTexts) {
//            processRawText(each);
//        }

        RawText rawText = DummyData.getDummyRawText();
        processRawText(rawText);
    }

    private void initFirebaseDatabase() {
        mRawTextsDatabaseReference = FirebaseDatabase.getInstance().getReference(refName);
        mRawTextsDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RawText rawText = dataSnapshot.getValue(RawText.class);
                mRawTextAdapter.add(rawText);
                mRawTextAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ");
            }
        });
    }

    private void initView(){
        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRawTextListView = (RecyclerView) findViewById(R.id.rawtextListView);
        mRawTextEditText = (EditText) findViewById(R.id.rawtextEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize rawText ListView and its adapter
        List<RawText> rawTexts = new ArrayList<>();
        mRawTextAdapter = new RawTextAdapter(rawTexts);
        mRawTextListView.setAdapter(mRawTextAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a rawText
        // Enable Send button when there's text to send
        mRawTextEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mRawTextEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

//         Send button sends a rawText and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RawText rawText = new RawText("Samples",
                        mRawTextEditText.getText().toString());
                mRawTextsDatabaseReference.push().setValue(rawText);
                // Clear input box
                mRawTextEditText.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

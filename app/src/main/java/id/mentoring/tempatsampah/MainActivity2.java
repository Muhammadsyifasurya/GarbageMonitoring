package id.mentoring.tempatsampah;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    private TextView mTvJarak;
    private TextView mTvKapasitas;
    private int mJarakValue;
    private String mKapasitasValue;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPresenter();
    }

    private void initPresenter() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mJarakValue = Integer.parseInt(snapshot.child("Data/Kapasitas").getValue().toString());
                mKapasitasValue = snapshot.child("Data/Kapasitas").getValue().toString();
                mTvJarak.setText(mKapasitasValue);

                if(mJarakValue > 20) {
                    mTvKapasitas.setText("KOSONG");
                }

                else if (mJarakValue >= 10 && mJarakValue <= 20){
                    mTvKapasitas.setText("HAMPIR PENUH");
                }

                else if (mJarakValue < 10) {
                    mTvKapasitas.setText("PENUH");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        mTvJarak = findViewById(R.id.tv_sampah);
        mTvKapasitas = findViewById(R.id.tv_kapasitas);
    }

}
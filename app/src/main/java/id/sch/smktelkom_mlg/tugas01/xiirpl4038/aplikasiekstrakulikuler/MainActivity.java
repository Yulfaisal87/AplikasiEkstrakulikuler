package id.sch.smktelkom_mlg.tugas01.xiirpl4038.aplikasiekstrakulikuler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNama;
    EditText etAlamat;
    EditText etTahun;
    RadioGroup rgJK;
    RadioGroup rgAG;
    CheckBox cbV, cbB, cbF;
    Button bOK;
    Spinner spProvinsi, spKota;
    TextView tvHasil, tvEks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

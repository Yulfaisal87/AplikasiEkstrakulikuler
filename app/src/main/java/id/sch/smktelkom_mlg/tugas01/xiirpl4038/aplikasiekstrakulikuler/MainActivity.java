package id.sch.smktelkom_mlg.tugas01.xiirpl4038.aplikasiekstrakulikuler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.tugas01.xiirpl4038.aplikasiekstrakulikuler.adapter.KotaAdapter;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    EditText etAlamat;
    EditText etTahun;
    RadioGroup rgJK;
    RadioGroup rgAG;
    CheckBox cbV, cbB, cbF;
    Button bOK;
    Spinner spProvinsi, spKota;
    TextView tvHasil, tvEks;

    int nEks;

    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta TImur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang",
            "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList<>();
    KotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etAlamat = (EditText) findViewById(R.id.editTextAlamat);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        rgJK = (RadioGroup) findViewById(R.id.RadioGroupJk);
        rgAG = (RadioGroup) findViewById(R.id.RadioGroupAG);

        cbV = (CheckBox) findViewById(R.id.checkBoxBING);
        cbB = (CheckBox) findViewById(R.id.checkBoxBI);
        cbF = (CheckBox) findViewById(R.id.checkBoxMTK);

        cbV.setOnCheckedChangeListener(this);
        cbB.setOnCheckedChangeListener(this);
        cbF.setOnCheckedChangeListener(this);

        bOK = (Button) findViewById(R.id.buttonOK);

        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);

        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvEks = (TextView) findViewById(R.id.textViewEkskul);

        adapter = new KotaAdapter(this, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[position]));
                adapter.setmProvinsi((String) spProvinsi.getItemAtPosition(position));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });


    }

    private void doProcess() {
        String hasil = null;
        String hasil1 = null;
        String hasil2 = null;

        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            String alamat = etAlamat.getText().toString();

            if (rgJK.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgJK.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }

            if (rgAG.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgAG.getCheckedRadioButtonId());
                hasil2 = rb.getText().toString();
            }

            StringBuilder builder = new StringBuilder();
            builder.append(" Kota ");
            builder.append(spKota.getSelectedItem().toString());


            String hasil3 = "Ekskul yang diikuti :\n";
            int Startlen = hasil3.length();
            if (cbV.isChecked()) hasil3 += cbV.getText() + "\n";
            if (cbB.isChecked()) hasil3 += cbB.getText() + "\n";
            if (cbF.isChecked()) hasil3 += cbF.getText() + "\n";

            if (hasil == null) {
                tvHasil.setText("Belum Memilih Jenis Kelamin");
            } else if (hasil2 == null) {
                tvHasil.setText("Belum Memilih Agama");
            } else if (hasil3.length() == Startlen) hasil3 += "Tidak Ada Pilihan";
            else {
                tvHasil.setText("Nama Lengkap : " + nama + "\nAlamat: " + alamat + " \nTahun: " + tahun + " \nJenis Kelamin : " + hasil + "\nAgama : " + hasil2
                        + "\n" + hasil3 + "Kota Asal : " + builder);
            }

        }

    }

    public boolean isValid() {
        boolean valid = true;
        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();
        String alamat = etAlamat.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }
        if (alamat.isEmpty()) {
            etAlamat.setError("Alamat belum diisi");
            valid = false;
        } else if (alamat.length() > 100) {
            etAlamat.setError("Alamat maksimal 100 karakter");
            valid = false;
        } else {
            etAlamat.setError(null);
        }
        if (tahun.isEmpty()) {
            etTahun.setError("Tahun Kelahiran belum diisi");
            valid = false;
        } else if (tahun.length() != 4) {
            etTahun.setError("Format Tahun Kelahiran bukan yyyy");
            valid = false;
        } else if (Integer.parseInt(etTahun.getText().toString()) >= 2016) {
            etTahun.setError("Input tahun anda melebihi angka 2016");
            valid = false;
        } else {
            etTahun.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nEks += 1;
        else nEks -= 1;
        tvEks.setText("Ekskul (" + nEks + " terpilih)");
    }
}


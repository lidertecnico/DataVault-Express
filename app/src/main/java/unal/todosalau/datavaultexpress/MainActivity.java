package unal.todosalau.datavaultexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;

import unal.todosalau.datavaultexpress.modelos.Recordatorio;

public class MainActivity extends AppCompatActivity {

private EditText editTextTitle;
private EditText editTextDescription;
private RadioGroup radioGroup;
private RadioButton radioButtonRecordatorio;
private RadioButton radioButtonPersonal;
private Button buttonSave;
private  Button buttonSearch;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextTitle = findViewById(R.id.editTextTitle);
    editTextDescription = findViewById(R.id.editTextDescription);
    radioGroup = findViewById(R.id.radioGroup);
    radioButtonRecordatorio = findViewById(R.id.radioButtonRecordatorio);
    radioButtonPersonal = findViewById(R.id.radioButtonPersonal);
    buttonSave = findViewById(R.id.buttonSave);
    buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SearchTitle();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelSave();
                CleanEditText();
            }
        });
    }

    protected void CleanEditText(){
        editTextTitle.setText("");
        editTextDescription.setText("");
        editTextTitle.setHint("Ingrese un titulo para el recordatorio");
        editTextDescription.setHint("Ingrese una descripción para el recordatorio");
    }
    protected String recordatorioToJson(Recordatorio recordatorio) {
        return "{" +
            "\"localDate\":\"" + recordatorio.getLocalDate() + "\"," +
            "\"radioButonSelection\":\"" + recordatorio.getRadioButonSelection() + "\"," +
            "\"title\":\"" + recordatorio.getTitle() + "\"," +
            "\"selection\":\"" + recordatorio.getSelection() + "\"" +
            "}";
    }
    protected void modelSave(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String radioButtonSelection = radioButtonRecordatorio.isChecked() ? "Ecologíco" :
                "Personal";

        Recordatorio recordatorio = new Recordatorio(radioButtonSelection, title, description);

        try {
            File file = new File(getFilesDir(), "recordatorio.json");
            if (!file.exists()) {
                file.createNewFile();
            }

            String content = new String(Files.readAllBytes(file.toPath()));
            if (content.contains("\"title\":\"" + title + "\"")) {
                Toast.makeText(MainActivity.this, "El título del recordatorio ya existe", Toast.LENGTH_SHORT).show();
                return;
            }

            String json = recordatorioToJson(recordatorio);
            FileWriter writer = new FileWriter(file, true);
            writer.write(json);
            writer.close();

            Toast.makeText(MainActivity.this, "Recordatorio guardado", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error al guardar el recordatorio", Toast.LENGTH_SHORT).show();
        }
    }
    protected void SearchTitle(){
        Intent intent = new Intent(MainActivity.this, ShowData.class);
        startActivity(intent);
        }

}
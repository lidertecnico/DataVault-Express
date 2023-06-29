package unal.todosalau.datavaultexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ShowData extends AppCompatActivity {
private EditText editTextSearch;
private Button buttonSearch;
private Button buttonBack;
private TextView textViewData;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_data);

    editTextSearch = findViewById(R.id.editTextSearch);
    buttonSearch = findViewById(R.id.buttonSearch);
    buttonBack = findViewById(R.id.buttonBack);
    textViewData = findViewById(R.id.textViewData);

    buttonSearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          SearchIntoFile();
            }
        });

    buttonBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ComeBackHome();
        }
    });
    }
    protected void SearchIntoFile(){
        String searchTerm = editTextSearch.getText().toString();
        try {
            File file = new File(getFilesDir(), "recordatorio.json");
            if (!file.exists()) {
                Toast.makeText(ShowData.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                return;
            }

            String content = new String(Files.readAllBytes(file.toPath()));
            String[] records = content.split("\\}\\{");

            StringBuilder sb = new StringBuilder();
            for (String record : records) {
                if (record.contains("\"title\":\"" + searchTerm + "\"") ||
                        record.contains("\"description\":\"" + searchTerm + "\"")) {
                    sb.append(record).append("\n");
                }
            }

            textViewData.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ShowData.this, "Error al buscar los datos", Toast.LENGTH_SHORT).show();
        }
    }
    protected void ComeBackHome(){
        Intent intent = new Intent(ShowData.this, MainActivity.class);
        startActivity(intent);
    }
}
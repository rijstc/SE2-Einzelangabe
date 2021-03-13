package com.example.se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send = (Button) findViewById(R.id.buttonSendToServer);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToServer(v);
            }
        });
    }

    private void sendToServer(View v) {
        EditText input = (EditText) findViewById(R.id.studentNumber);
        String studentNumber = input.getText().toString();
        TextView answer = (TextView) findViewById(R.id.serverAnswer);
        String answerFromServer;
        TCPClient client = new TCPClient(studentNumber);
        Thread thread = new Thread(client);
        thread.start();
        try {
            thread.join(1000);
            answerFromServer = client.getServerAnswer();
        } catch (InterruptedException e) {
            answerFromServer = "Fehler: "+e;
        }
        answer.setText(answerFromServer);
    }

}
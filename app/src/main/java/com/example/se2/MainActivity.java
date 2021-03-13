package com.example.se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

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
        Button sort = (Button) findViewById(R.id.buttonSort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort(v);
            }
        });
        
    }

    private void sort(View v) {
        String studentNumber = getInput();
        TextView answer = (TextView) findViewById(R.id.serverAnswer);
        String sorted;
        StringBuffer sb = new StringBuffer();
        ArrayList<Integer> evenNum = new ArrayList<Integer>();
        ArrayList<Integer> oddNum = new ArrayList<Integer>();
        for (int i = 0; i < studentNumber.length(); i++) {
            int temp;
            temp = Integer.parseInt(""+studentNumber.charAt(i));
            if (temp%2 == 0){
                evenNum.add(temp);
            } else {
                oddNum.add(temp);
            }
        }
        Collections.sort(evenNum);
        Collections.sort(oddNum);
        for (int i=0; i<evenNum.size(); i++) {
            sb.append(evenNum.get(i));
        }
        for (int i = 0; i < oddNum.size(); i++) {
           sb.append(oddNum.get(i));
        }
        sorted = sb.toString();

        answer.setText(sorted);
    }

    private String getInput() {
        EditText input = (EditText) findViewById(R.id.studentNumber);
        return input.getText().toString();
    }

    private void sendToServer(View v) {
        String studentNumber = getInput();
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
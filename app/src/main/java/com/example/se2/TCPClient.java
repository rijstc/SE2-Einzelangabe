package com.example.se2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient implements Runnable{
    String studentNumber;
    String serverAnswer;
    String hostname = "se2-isys.aau.at";
    int port = 53212;

    TCPClient (String studentNumber){
        this.studentNumber = studentNumber;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(hostname,port);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(studentNumber +'\n');
            serverAnswer = inFromServer.readLine();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getServerAnswer() {
        return serverAnswer;
    }
}

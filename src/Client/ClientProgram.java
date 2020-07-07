package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProgram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String serverHost = "localhost";
        Socket socketClient = null;
        BufferedWriter bos = null;
        BufferedReader bis = null;
        BufferedReader win = null;

        try {
            socketClient = new Socket(serverHost, 1205);
            System.out.println("Successful Connection");
            bos = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            bis = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            win = new BufferedReader(new InputStreamReader(System.in));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            String m = "1", line = "2";
            while (!m.equals("_Quit")) {

                line = bis.readLine();
                if (line != null) {
                    System.out.println("Server: " + line);
                }
                m = win.readLine();
                bos.write(m);
                bos.newLine();
                bos.flush();
//                m = scan.nextLine();
//                bos.write(m);
//                bos.newLine();
//                bos.flush();
//                String Line;
//                while ((Line = bis.readLine()) != null) {
//                    if (Line.contains("Conversation End")) {
//                        break;
//                    }
//                    System.out.println("Client" + Line);
//                }
            }
            bos.close();
            bis.close();
            socketClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Try to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}

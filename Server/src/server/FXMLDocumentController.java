/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author tulio
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private JFXButton btClose;
    @FXML
    private JFXButton btStartServer;
    @FXML
    private JFXButton btStopServer;
    @FXML
    private JFXTextField tfServerStatus;

    ServerSocket server = null;
    Socket socket = null;
    BufferedReader input = null;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    private void startServer()
    {

    }

    @FXML
    private void clkClose(ActionEvent event)
    {
        if(server != null)
        {
            int choice = JOptionPane.showConfirmDialog(null, "Servidor ainda está rodando, deseja encerrá-lo?");
            
            System.out.println(choice);
            
            if(choice == 0)
                Platform.exit();
        }
        else
            Platform.exit();
    }

    @FXML
    private void clkStartServer(ActionEvent event)
    {
        try
        {
            /// CRIANDO O SERVER NA PORTA 6669 SE ESSA ESTIVER DISPONÍVEL
            server = new ServerSocket(6669);

            /// AGUARDA A CONEXÃO NA PORTA E RETORNA O SOCKET ONDE SERÁ FEITA A CONEXÃO
            socket = server.accept();

            /// CRIA O BUFERREDREADER DO CANAL DE ENTRADA DO SOCKET
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(input.readLine());
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void clkStopServer(ActionEvent event)
    {
        try
        {
            /// ENCERRANDO SOCKET E SERVIDOR
            socket.close();
            server.close();
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

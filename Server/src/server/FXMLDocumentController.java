/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import server.Modelos.Users;

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

    private ThreadServer serverThread;
    private Thread threadPrincipal;
    private ServerSocket server = null;
    private Socket socket = null;
    private BufferedReader input = null;
    private boolean open;
    private ArrayList<Users> usuarios;

    @FXML
    private TableView<Users> tbClientes;
    @FXML
    private JFXTextArea taMensagens;
    @FXML
    private TableColumn<Users, String> colNome;
    @FXML
    private TableColumn<Users, String> colIP;
    @FXML
    private TableColumn<Users, String> colPorta;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        open = false;

        tfServerStatus.setText("Offline...");
        tfServerStatus.setUnFocusColor(Color.RED);

        usuarios = new ArrayList<Users>();

        this.colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        this.colIP.setCellValueFactory(new PropertyValueFactory<>("IP"));
        this.colPorta.setCellValueFactory(new PropertyValueFactory<>("Porta"));
    }

    @FXML
    private void clkClose(ActionEvent event)
    {
        if (open)
        {
            int choice = JOptionPane.showConfirmDialog(null, "Servidor ainda está rodando, deseja encerrá-lo?");
            if (choice == 0)
            {
                clkStopServer(event);
                Platform.exit();
            }
        } else
        {
            clkStopServer(event);
            Platform.exit();
        }
    }

    @FXML
    private void clkStartServer(ActionEvent event)
    {
        tfServerStatus.setText("Online...");
        tfServerStatus.setUnFocusColor(Color.GREEN);
        try
        {
            this.server = new ServerSocket(6669);
            this.serverThread = new ThreadServer(socket, server, this);
            this.threadPrincipal = new Thread(this.serverThread);
            this.threadPrincipal.start();
            open = true;
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void clkStopServer(ActionEvent event)
    {
        tfServerStatus.setText("Offline...");
        tfServerStatus.setUnFocusColor(Color.RED);
        try
        {
            open = false;
            server.close();
            threadPrincipal.stop();
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public boolean isOpen()
    {
        return open;
    }

    public void novaConexao(String s)
    {
        String[] nc = s.split("#");

        Users newUser = new Users(nc[2].replace("IPCLIENT=", ""), Integer.parseInt(nc[4].replace("PORTA=", "")), nc[3].replace("NOME=", ""));

        usuarios.add(newUser);

        tbClientes.getItems().add(newUser);
        tbClientes.refresh();
    }
}

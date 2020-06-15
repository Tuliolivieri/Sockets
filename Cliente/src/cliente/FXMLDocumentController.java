/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author tulio
 */
public class FXMLDocumentController implements Initializable
{
    @FXML
    private JFXTextField tfUser;
    @FXML
    private JFXTextField tfTime;
    @FXML
    private JFXTextField tfIP;
    @FXML
    private JFXButton btClose;
    @FXML
    private JFXButton btMinMax;
    @FXML
    private BorderPane borderPanel;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfPorta;
    @FXML
    private JFXButton btConectar;
    
    private Socket socket = null;
    private PrintStream ps = null;
    @FXML
    private JFXTextField tfIPorta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        LocalDate data = LocalDate.now();
        tfTime.setText(data.getDayOfWeek() + ", " + data.getMonth() + " " + data.getDayOfMonth() + ", " + data.getYear());
    }

    @FXML
    private void clkClose(ActionEvent event)
    {
        Platform.exit();
    }

    @FXML
    private void clkMinMax(ActionEvent event)
    {
        Stage stage = (Stage) btMinMax.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void clkConectar(ActionEvent event)
    {
        try
        {
            socket = new Socket("127.0.0.1", 6669);
            ps = new PrintStream(socket.getOutputStream());
            ps.println("#@CONNECT#IPCLIENT=" + tfIP.getText() +"#NOME="+ tfNome.getText() +"#PORTA=" + tfPorta.getText());
            tfUser.setText(tfNome.getText());
            tfIPorta.setText(tfIP.getText() + ":" + tfPorta.getText());
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        } finally
        {
            try
            {
                socket.close();
            } catch (Exception e)
            {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

}

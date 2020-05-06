/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author tulio
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Socket socket = null;
        PrintStream ps= null;
        
        try
        {
            socket = new Socket("127.0.0.1", 6669);
            ps = new PrintStream(socket.getOutputStream());
            ps.println("Enviei mai uma linhazinha hehehe");
        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }finally
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

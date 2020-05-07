/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;
import javafx.scene.control.Alert;
import server.Modelos.Users;

/**
 *
 * @author tulio
 */
public class ThreadMensagem implements Runnable
{
     private FXMLDocumentController tela;
    private Users users;
    private String mensagem;
    private String instruction;

    ThreadMensagem(Socket socket, FXMLDocumentController tela)
    {
        this.tela = tela;
        users = new Users(socket.getInetAddress().toString(),socket.getPort(), "Anon");
    }

    @Override
    public void run()
    {
        /*do
        {
            try
            {
                
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro na Thread de Mensagens Servidor: " + e.getMessage());
            }
        }while(!instruction.equals(Chat.DISCONNECT));*/
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author tulio
 */
public class ThreadServer implements Runnable
{

    private FXMLDocumentController tela;
    private Socket socket;
    private ServerSocket server;
    private ThreadMensagem thMensagem;
    private Thread newThread;
    private BufferedReader input;
    private String mensagem;

    public ThreadServer(Socket socket, ServerSocket server, FXMLDocumentController tela)
    {
        this.socket = socket;
        this.server = server;
        this.tela = tela;
    }

    @Override
    public void run()
    {
        do
        {
            try
            {
                socket = server.accept();
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                mensagem = input.readLine();
                tela.novaConexao(mensagem);
                thMensagem = new ThreadMensagem(socket, tela);
                newThread = new Thread(thMensagem);
                newThread.start();
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro na Thread do Servidor: " + e.getMessage());
            }
        } while (tela.isOpen());
    }

}
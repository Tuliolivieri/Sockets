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

/**
 *
 * @author tulio
 */
public class Server
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader input = null;
        
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
        }finally
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
    
}

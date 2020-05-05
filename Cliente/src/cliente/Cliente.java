/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author tulio
 */
public class Cliente
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
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

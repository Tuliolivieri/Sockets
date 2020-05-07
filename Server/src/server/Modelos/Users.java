/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.Modelos;

/**
 *
 * @author tulio
 */
public class Users
{
    private int Porta;
    private String Nome, IP;

    public Users()
    {
        Nome = "Anon";
        IP = "-";
        Porta = 0;
    }

    public Users(String IP, int Porta, String Nome)
    {
        this.IP = IP;
        this.Porta = Porta;
        this.Nome = Nome;
    }

    public String getIP()
    {
        return IP;
    }

    public void setIP(String IP)
    {
        this.IP = IP;
    }

    public int getPorta()
    {
        return Porta;
    }

    public void setPorta(int Porta)
    {
        this.Porta = Porta;
    }

    public String getNome()
    {
        return Nome;
    }

    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }
    
    @Override
    public String toString()
    {
        return "[Nome: " + Nome + " / IP: " + IP + "/ Porta: " + Porta + "]";
    }
}

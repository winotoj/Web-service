/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpcone.client;
import java.net.URL;
import java.util.Scanner;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author minx1
 */
public class RpconeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        //client setup
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://127.0.0.1:8080/XmlRpcServlet"));
        
        //instantiate client and give it to config
        
       XmlRpcClient client = new XmlRpcClient();
       client.setConfig(config);
       //make the call: echo1.echo("Hello via RPC)
       //with this librar, parameter must be send as object array and cant send primitive
       Object[] params = new Object[]{"hello via RPC"};       
       String result = (String) client.execute("echo1.echo", params);
        System.out.println("Result is: " +result);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        double first = sc.nextDouble();
        //Double first = Double.parseDouble(sc.nextLine());
        System.out.println("Enter the second number: ");
        double second = sc.nextDouble();
       // Double second = Double.parseDouble(sc.nextLine());       
        Object[] calcs = new Object[]{first, second};
        String resultCalc = String.valueOf(client.execute("calc1.calc", calcs));
        System.out.println("The result is " + resultCalc);
        
       
    }
    
}
0
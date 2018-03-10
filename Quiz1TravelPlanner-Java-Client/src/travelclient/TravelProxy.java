/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author 1795661
 */
public class TravelProxy {
    XmlRpcClient client;
    public TravelProxy() throws IOException {
        try {
            // client setup
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://127.0.0.1:6543/XmlRpcServlet"));
            // instantiate client and give it config
            client = new XmlRpcClient();
            client.setConfig(config);
        } catch (MalformedURLException ex) {
            throw new IOException("Error initializing XML-RPC client", ex);
        }
    }
    
    public int addTravel(String name, String passport, double cost) throws IOException{
        try {
            Object[] params = new Object[]{name, passport, cost};
            client.execute("travel.addTravel", params);
            return 0;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addTravel call", ex);
        }
    }
    public String[] getAllBooks() throws IOException {
        try {
            Object[] objArray = (Object[]) client.execute("travel.getAllTravels", new Object[0]);
            String[] strArray = Arrays.copyOf(objArray, objArray.length, String[].class);
            return strArray;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC getAllTravels call", ex);
        }
    }
    
    
}

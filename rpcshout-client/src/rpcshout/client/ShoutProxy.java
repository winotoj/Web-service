/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpcshout.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import rpcshout.pkgint.ShoutInterface;

/**
 *
 * @author 1795661
 */
public class ShoutProxy implements ShoutInterface{
// this is our model that connected to api
    private XmlRpcClient client;
    public ShoutProxy() throws Exception{
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://127.0.0.1:8181/XmlRpcServlet"));           
            //instantiate client and give it to config
            
            client = new XmlRpcClient();
            client.setConfig(config);
        } catch (MalformedURLException ex) {
            throw new IOException("Error initialize XML-RPC client", ex);
        }
    }

    @Override
    public int addShout(String msg) throws IOException{
        try {
            Object[] params = new Object[]{"hello via RPC"};
            client.execute("shout.addShout", params);
            return 0;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addShout call", ex);
        }
    }

    @Override
    public String[] getAllShouts() throws IOException {
        try {
            Object[] objArray = (Object[]) client.execute("shout.getAllShouts", new Object[0]);
            String[] strArray = Arrays.copyOf(objArray, objArray.length, String[].class);
            return strArray;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addShout call", ex);
        }
    }
    
}

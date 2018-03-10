/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeclientshout;

import homeintshout.ShoutInterface;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author minx1
 */
public class ShoutProxy implements ShoutInterface{

    private XmlRpcClient client;

    // FIXME: turn these into IOExceptions
    public ShoutProxy() throws IOException {
        try {
            // client setup
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://127.0.0.1:9001/XmlRpcServlet"));
            // instantiate client and give it config
            client = new XmlRpcClient();
            client.setConfig(config);
        } catch (MalformedURLException ex) {
            throw new IOException("Error initializing XML-RPC client", ex);
        }
    }
    @Override
    public int addShout(String msg) throws IOException {
        try {
            Object[] params = new Object[]{msg};
            client.execute("shout.addShout", params);
            return 0;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addShout call", ex);
        }
    }

    @Override
    public String[] getAllshouts() throws IOException {
        try {
            Object[] objArray = (Object[]) client.execute("shout.getAllshouts", new Object[0]);
            String[] strArray = Arrays.copyOf(objArray, objArray.length, String[].class);
            return strArray;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addShout call", ex);
        }
    }
    
}

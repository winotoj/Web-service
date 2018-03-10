/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpclib.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import rpclib.libint.LibraryInt;

/**
 *
 * @author 1795661
 */
public class LibraryProxy implements LibraryInt{
   private XmlRpcClient client;
    public LibraryProxy() throws IOException {
        try {
            // client setup
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://127.0.0.1:7272/XmlRpcServlet"));
            // instantiate client and give it config
            client = new XmlRpcClient();
            client.setConfig(config);
        } catch (MalformedURLException ex) {
            throw new IOException("Error initializing XML-RPC client", ex);
        }
    }
   
     @Override
    public int addBook(String title, String author, int yop) throws IOException {
         try {
            Object[] params = new Object[]{title, author, yop};
            client.execute("library.addBook", params);
            return 0;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC addBook call", ex);
        }
    }
    @Override
    public String[] getAllBooks() throws IOException {
        try {
            Object[] objArray = (Object[]) client.execute("library.getAllBooks", new Object[0]);
            String[] strArray = Arrays.copyOf(objArray, objArray.length, String[].class);
            return strArray;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC getallbook call", ex);
        }
    }

    @Override
    public String[] getFilteredBooks(String keyword) throws IOException {
        try {
            Object[] objArray = (Object[]) client.execute("library.getFilteredBooks", new Object[]{keyword});
            String[] strArray = Arrays.copyOf(objArray, objArray.length, String[].class);
            return strArray;
        } catch (XmlRpcException ex) {
            throw new IOException("Error in XML-RPC getfrilterbook call", ex);
        }
    }

   

//    @Override
//    public String[] getFilteredBooks() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}

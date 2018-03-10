/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpcone.server;

import java.util.HashSet;
import java.util.Set;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;


/**
 *
 * @author minx1
 */
public class RpconeServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // create web server and xml-rpc server connected to it
        WebServer webServer = new WebServer(8080);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        
        //tell XML-RPC server which classes contain methods to map (make available)
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("echo1", Echo.class);
        phm.addHandler("calc1", Calculator.class);
        xmlRpcServer.setHandlerMapping(phm);
        
        //some additional we need 
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);
        //
        webServer.start();
        
    }
    
}

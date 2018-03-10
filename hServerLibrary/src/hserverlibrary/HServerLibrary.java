/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hserverlibrary;

import java.io.IOException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author minx1
 */
public class HServerLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer(9002);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        
        // tell XML-RPC server which classes contain methods to map (make available)
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("library", LibraryService.class);
        // phm.addHandler(...., pClass); you can add multiple handlers
        xmlRpcServer.setHandlerMapping(phm);
        
        // some additional options we need
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);
        //
        System.out.println("Server started");
        webServer.start();
    }
}

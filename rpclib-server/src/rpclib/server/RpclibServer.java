package rpclib.server;

import java.io.IOException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class RpclibServer {

    public static void main(String[] args) throws Exception {
       // only to check if actually create the sqlite 
       // new LibraryService();
        // create web server and XML-RPC server connected to it
        WebServer webServer = new WebServer(7272);
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

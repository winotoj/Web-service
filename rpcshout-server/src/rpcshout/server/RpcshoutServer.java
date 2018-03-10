
package rpcshout.server;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author 1795661
 */
public class RpcshoutServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // create web server and xml-rpc server connected to it
        WebServer webServer = new WebServer(8181);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        
        //tell XML-RPC server which classes contain methods to map (make available)
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.addHandler("shout", ShoutService.class);
        xmlRpcServer.setHandlerMapping(phm);
        
        //some additional we need 
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);
        //
        webServer.start();
        // TODO code application logic here
    }
    
}

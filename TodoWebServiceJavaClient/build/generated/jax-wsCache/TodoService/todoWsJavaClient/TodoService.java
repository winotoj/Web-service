
package todoWsJavaClient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TodoService", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://localhost:14177/TodoService.svc?wsdl")
public class TodoService
    extends Service
{

    private final static URL TODOSERVICE_WSDL_LOCATION;
    private final static WebServiceException TODOSERVICE_EXCEPTION;
    private final static QName TODOSERVICE_QNAME = new QName("http://tempuri.org/", "TodoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:14177/TodoService.svc?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TODOSERVICE_WSDL_LOCATION = url;
        TODOSERVICE_EXCEPTION = e;
    }

    public TodoService() {
        super(__getWsdlLocation(), TODOSERVICE_QNAME);
    }

    public TodoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TODOSERVICE_QNAME, features);
    }

    public TodoService(URL wsdlLocation) {
        super(wsdlLocation, TODOSERVICE_QNAME);
    }

    public TodoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TODOSERVICE_QNAME, features);
    }

    public TodoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TodoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ITodoService
     */
    @WebEndpoint(name = "BasicHttpBinding_ITodoService")
    public ITodoService getBasicHttpBindingITodoService() {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_ITodoService"), ITodoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ITodoService
     */
    @WebEndpoint(name = "BasicHttpBinding_ITodoService")
    public ITodoService getBasicHttpBindingITodoService(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_ITodoService"), ITodoService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TODOSERVICE_EXCEPTION!= null) {
            throw TODOSERVICE_EXCEPTION;
        }
        return TODOSERVICE_WSDL_LOCATION;
    }

}

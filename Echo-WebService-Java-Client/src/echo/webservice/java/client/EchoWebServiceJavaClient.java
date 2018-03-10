/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echo.webservice.java.client;

/**
 *
 * @author 1795661
 */
public class EchoWebServiceJavaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IService1 client = new Service1().getBasicHttpBindingIService1();
        String msg = client.echoMessage("this is a test");
        System.out.println(msg);
    }
}

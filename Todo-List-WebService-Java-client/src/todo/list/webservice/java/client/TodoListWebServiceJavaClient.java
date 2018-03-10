/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list.webservice.java.client;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author 1795661
 */
public class TodoListWebServiceJavaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        IService1 client = new Service1().getBasicHttpBindingIService1();
        ObjectFactory factory = new ObjectFactory();
        
        Todo todo = new Todo();
        todo.setTask(factory.createString("Buy Milk"));
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        todo.setDueDate(date2);
        todo.setIsDone(Boolean.FALSE);
        client.addTodo(todo);
        
        
        
        
    }
    
}

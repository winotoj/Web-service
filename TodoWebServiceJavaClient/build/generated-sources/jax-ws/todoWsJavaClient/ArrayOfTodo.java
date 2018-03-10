
package todoWsJavaClient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTodo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTodo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Todo" type="{http://schemas.datacontract.org/2004/07/WcfTodoServer}Todo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTodo", namespace = "http://schemas.datacontract.org/2004/07/WcfTodoServer", propOrder = {
    "todo"
})
public class ArrayOfTodo {

    @XmlElement(name = "Todo", nillable = true)
    protected List<Todo> todo;

    /**
     * Gets the value of the todo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the todo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTodo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Todo }
     * 
     * 
     */
    public List<Todo> getTodo() {
        if (todo == null) {
            todo = new ArrayList<Todo>();
        }
        return this.todo;
    }

}

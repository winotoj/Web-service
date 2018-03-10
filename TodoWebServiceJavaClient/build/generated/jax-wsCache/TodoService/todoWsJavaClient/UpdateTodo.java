
package todoWsJavaClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="todo" type="{http://schemas.datacontract.org/2004/07/WcfTodoServer}Todo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "todo"
})
@XmlRootElement(name = "UpdateTodo")
public class UpdateTodo {

    @XmlElementRef(name = "todo", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Todo> todo;

    /**
     * Gets the value of the todo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Todo }{@code >}
     *     
     */
    public JAXBElement<Todo> getTodo() {
        return todo;
    }

    /**
     * Sets the value of the todo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Todo }{@code >}
     *     
     */
    public void setTodo(JAXBElement<Todo> value) {
        this.todo = value;
    }

}

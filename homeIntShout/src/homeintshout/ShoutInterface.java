/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeintshout;

import java.io.IOException;

/**
 *
 * @author minx1
 */
public interface ShoutInterface {
 // !!!!!!!!!!!!!! WARNING !!!!!!!!!!!!!!!
    // OUR XML-RPC LIBRARY CAN'T DEAL WITH METHODS RETURNING VOID
    // THIS MUST RETURN INT
    // !!!!!!!!!!!!!! WARNING !!!!!!!!!!!!!!!    
    public int addShout (String msg) throws IOException;
    public String[] getAllshouts() throws IOException;
}
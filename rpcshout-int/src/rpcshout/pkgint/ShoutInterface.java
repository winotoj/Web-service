/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpcshout.pkgint;

import java.io.IOException;

/**
 *
 * @author 1795661
 */

//------------------WARNING-----------------
//OUR RPC-XML CANNOT RETURN VOID, CHANGE TO INT
public interface ShoutInterface {
    public int addShout(String msg) throws IOException;
    public String[] getAllShouts() throws IOException;
    
}

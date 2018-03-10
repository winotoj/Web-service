
package rpcshout.server;

import java.util.ArrayList;
import rpcshout.pkgint.ShoutInterface;

/**
 *
 * @author 1795661
 */
public class ShoutService implements ShoutInterface {

    private ArrayList<String> shoutList = new ArrayList<>();
    
    @Override
    public int addShout(String msg) {
        return 0;
    }

    @Override
    public String[] getAllShouts() {
        return shoutList.toArray(new String[0]);
    }
    
}

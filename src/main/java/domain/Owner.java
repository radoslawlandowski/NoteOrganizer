package domain;

import java.security.Principal;

/**
 * Created by radoslawlandowski on 09.10.16.
 */
public class Owner implements Principal, Cloneable {

    private String email;

    @Override
    public String getName() {
        return this.email;
    }
}

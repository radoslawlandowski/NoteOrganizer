package auth;

import domain.Owner;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;


/**
 * Created by radoslawlandowski on 09.10.16.
 */
public class NoteOrganizerAuthenticator implements Authenticator<BasicCredentials, Owner> {

    @Override
    public Optional<Owner> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("secret".equals(credentials.getPassword())) {
            return Optional.of(new Owner());
        }
        return Optional.empty();
    }
}
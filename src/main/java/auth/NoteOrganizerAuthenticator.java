package auth;

import domain.Owner;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;


public class NoteOrganizerAuthenticator implements Authenticator<BasicCredentials, Owner> {

    @Override
    public Optional<Owner> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if(credentials.getPassword().equals("password")) {
            return Optional.ofNullable(new Owner(credentials.getUsername(), credentials.getPassword()));
        } else {
            return Optional.empty();
        }
    }
}

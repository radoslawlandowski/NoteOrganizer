/**
 * Created by radoslawl on 27/09/16.
 */

import DAO.NoteDAO;
import DAO.TabDAO;
import auth.NoteOrganizerAuthenticator;
import com.hubspot.dropwizard.guice.GuiceBundle;
import configuration.NoteOrganizerConfiguration;
import domain.Note;
import domain.Owner;
import domain.Tab;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import resources.NoteResource;
import resources.TabResource;


public class NoteOrganizerApplication extends Application<NoteOrganizerConfiguration> {
    public static final Logger LOGGER = Logger.getLogger(NoteOrganizerApplication.class);

    private final HibernateBundle<NoteOrganizerConfiguration> hibernate = new HibernateBundle<NoteOrganizerConfiguration>(Note.class, Tab.class, Owner.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(NoteOrganizerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private GuiceBundle<NoteOrganizerConfiguration> guiceBundle;

    public static void main(final String[] args) throws Exception {
        new NoteOrganizerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<NoteOrganizerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);

        guiceBundle = GuiceBundle.<NoteOrganizerConfiguration>newBuilder()
                .addModule(new NoteOrganizerModule())
                .setConfigClass(NoteOrganizerConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final NoteOrganizerConfiguration configuration, final Environment environment) throws Exception {
        LOGGER.info("Application name: " + configuration.getAppName());

        environment.jersey().register(guiceBundle.getInjector().getInstance(NoteDAO.class));
        environment.jersey().register(guiceBundle.getInjector().getInstance(TabDAO.class));


        final NoteDAO noteDao = new NoteDAO(hibernate.getSessionFactory());
        final TabDAO tabDao = new TabDAO(hibernate.getSessionFactory());

        environment.jersey().register(new NoteResource(noteDao));
        environment.jersey().register(new TabResource(tabDao));

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Owner>()
                        .setAuthenticator(new NoteOrganizerAuthenticator())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Owner.class));
    }
}
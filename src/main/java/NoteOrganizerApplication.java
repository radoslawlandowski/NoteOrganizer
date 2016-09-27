/**
 * Created by radoslawl on 27/09/16.
 */

import configuration.NoteOrganizerConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoteOrganizerApplication extends Application<NoteOrganizerConfiguration> {
    public static final Logger LOGGER = LoggerFactory.getLogger(NoteOrganizerApplication.class);

    public static void main(final String[] args) throws Exception {
        new NoteOrganizerApplication().run(args);
    }

    @Override
    public void run(final NoteOrganizerConfiguration configuration, final Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getAppName());
        LOGGER.info("Application description: {}", configuration.getApplicationDescr());
    }
}
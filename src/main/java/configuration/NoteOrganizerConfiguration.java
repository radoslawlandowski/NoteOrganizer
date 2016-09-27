package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class NoteOrganizerConfiguration extends Configuration {

    @NotEmpty
    private String appName;

    @NotEmpty
    private String applicationDescr;

    @JsonProperty
    public String getAppName() {
        return appName;
    }

    @JsonProperty
    public void setAppName(final String appName) {
        this.appName = appName;
    }

    @JsonProperty
    public String getApplicationDescr() {
        return applicationDescr;
    }

    @JsonProperty
    public void setApplicationDescr(String applicationDescr) {
        this.applicationDescr = applicationDescr;
    }
}
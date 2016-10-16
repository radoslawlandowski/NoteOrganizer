package domain;

/**
 * Created by radoslawlandowski on 09.10.16.
 */
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class NoteTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final String fixturesPath = "fixtures/";
    private final String fixture = "note.json";

    @Test
    public void serializesToJSON() throws Exception {
        String title = "title";
        String content = "content";
        Date date = new Date(0L);
        Owner owner = new Owner();
        String tab = "tab";

        final Note note = new Note(title, content, date, owner, tab);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture(fixturesPath + fixture), Note.class));

        assertThat(MAPPER.writeValueAsString(note)).isEqualTo(expected);
    }
}

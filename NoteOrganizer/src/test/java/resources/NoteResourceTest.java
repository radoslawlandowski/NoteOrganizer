//package resources;
//
///**
// * Created by radoslawlandowski on 09.10.16.
// */
//import DAO.NoteDAO;
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import domain.Note;
//import domain.Owner;
//import io.dropwizard.testing.junit.ResourceTestRule;
//import org.junit.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//public class NoteResourceTest {
//
//    private static final NoteDAO dao = mock(NoteDAO.class);
//
//    Injector injector = Guice.createInjector(new TestModule());
//
//    @ClassRule
//    public static final ResourceTestRule resources = ResourceTestRule.builder()
//            .addResource(new NoteResource(dao))
//            .build();
//
//    private Note note;
//    private Owner owner;
//
//    private List<Note> noteList;
//
//    @Before
//    public void setup() {
//
//        injector.injectMembers(dao);
//
//        owner = new Owner();
//        note = new Note("title", "content", new Date(0L) , owner, "tab");
//
//        noteList = new ArrayList<>();
//        noteList.add(note);
//
//        when(dao.all(owner)).thenReturn(noteList);
//    }
//
//    @After
//    public void tearDown() {
//        reset(dao);
//    }
//
//    @Test
//    @Ignore
//    public void testGetPerson() {
//        assertThat(resources.client().target("/notes/all").request().get())
//                .isEqualTo(noteList);
//        verify(dao).all(owner);
//    }
//}
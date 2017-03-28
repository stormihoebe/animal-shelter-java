import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class AnimalTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Animal_instantiatesCorrectly_true(){
    Animal testAnimal = new Animal("Spock", "male", "dog", "corgi");
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_instantiatesWithName_true(){
    Animal testAnimal = new Animal("Spock", "male", "dog", "corgi");
    assertEquals("Spock", testAnimal.getName());
  }

}

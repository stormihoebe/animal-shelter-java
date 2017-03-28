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
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void getName_instantiatesWithName_true(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals("Spock", testAnimal.getName());
  }

  @Test
  public void getSex_instantiatesWithSex_true(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals("male", testAnimal.getSex());
  }

  @Test
  public void getDoa_instantiatesDateAndTime_true(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals("2017-03-25", testAnimal.getDoa());
  }

  @Test
  public void getType_instantiatesWithType_true(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals("dog", testAnimal.getType());
  }

  @Test
  public void getBreed_instantiatesWithBreed_true(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals("corgi", testAnimal.getBreed());
  }

  @Test
  public void getOwnerId_instantiatesWithOwnerIDOf0ByDefault_0(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertEquals(0, testAnimal.getOwnerId());
  }

  @Test
  public void equals_returnsTrueIfAnimalsAreTheSame_true() {
    Animal firstAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    Animal secondAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    assertTrue(firstAnimal.equals(secondAnimal));
  }

  @Test
  public void save_savesAnimal() {
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    testAnimal.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }

  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal firstAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Bob", "male", "2016-03-25", "cat", "domestic shorthair");
    secondAnimal.save();
    assertEquals(true, Animal.all().get(0).equals(firstAnimal)); assertEquals(true, Animal.all().get(1).equals(secondAnimal));
  }

  @Test
  public void assignOwner_updateAssingOwnerID_1(){
    Animal testAnimal = new Animal("Spock", "male", "2017-03-25", "dog", "corgi");
    testAnimal.save();
    testAnimal.assignOwner(1);
    assertEquals(1, testAnimal.getOwnerId());
  }

}

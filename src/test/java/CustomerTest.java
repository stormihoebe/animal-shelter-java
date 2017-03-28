import org.sql2o.*;
import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CustomerTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM customer *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Customer_instantiatesCorrectly_true(){
    Customer testCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    assertEquals(true, testCustomer instanceof Customer);
  }

  @Test
  public void getPhone_instantiatesWithPhone_true(){
    Customer testCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    assertEquals("867-5309", testCustomer.getPhone());
  }

  @Test
  public void getBreedPref_instantiatesWithBreedPref_true(){
    Customer testCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    assertEquals("Corgi", testCustomer.getBreedPref());
  }

  @Test
  public void getTypePref_instantiatesWithTypePref_true(){
    Customer testCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    assertEquals("dog", testCustomer.getTypePref());
  }

  @Test
  public void all_returnsAllInstancesOfCustomer_true(){
    Customer firstCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    Customer secondCustomer = new Customer ("Stormi", "867-5309", "pigeon", "bird");
    firstCustomer.save();
    secondCustomer.save();
    assertEquals(true, Customer.all().get(0).equals(firstCustomer)); assertEquals(true, Customer.all().get(1).equals(secondCustomer));
  }

  @Test
  public void save_savesCustomer_true() {
    Customer testCustomer = new Customer ("Stormi", "867-5309", "pigeon", "bird");
    testCustomer.save();
    assertTrue(Customer.all().get(0).equals(testCustomer));
  }

  @Test
  public void find_returnsCustomerWithSameId_SecondCustomer(){
    Customer firstCustomer = new Customer ("Megan", "867-5309", "Corgi", "dog");
    Customer secondCustomer = new Customer ("Stormi", "867-5309", "pigeon", "bird");
    firstCustomer.save();
    secondCustomer.save();
    assertEquals(secondCustomer, Customer.find(secondCustomer.getId()));
  }
}

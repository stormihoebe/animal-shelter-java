import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Customer {
  private String name;
  private String phone;
  private String breed_pref;
  private String type_pref;
  private int id;

  public Customer(String name, String phone, String breed_pref, String type_pref) {
    this.name = name;
    this.phone = phone;
    this.breed_pref = breed_pref;
    this.type_pref = type_pref;
  }

  public String getName(){
    return name;
  }
  public String getPhone(){
    return phone;
  }
  public String getBreedPref(){
    return breed_pref;
  }
  public String getTypePref(){
    return type_pref;
  }
  public int getId(){
    return id;
  }
  public static List<Customer> all() {
    String sql = "SELECT id, name, phone, breed_pref, type_pref FROM customer";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Customer.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO customer(name, phone, breed_pref, type_pref) VALUES (:name, :phone, :breed_pref, :type_pref)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("phone", this.phone)
      .addParameter("breed_pref", this.breed_pref)
      .addParameter("type_pref", this.type_pref)
      .executeUpdate()
      .getKey();
    }
  }

  public static Customer find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM customer where id=:id";
      Customer customer = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Customer.class);
      return customer;
    }
  }


  @Override
  public boolean equals( Object otherCustomer){
    if(!(otherCustomer instanceof Customer)){
      return false;
    } else {
      Customer newCustomer = (Customer) otherCustomer;
      return this.getName().equals(newCustomer.getName());
    }
  }
}

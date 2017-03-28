import org.sql2o.*;
import java.util.List;
import java.time.LocalDateTime;


public class Animal {
  private String name;
  private String sex;
  private String doa;
  private String type;
  private String breed;
  private int id;
  private int owner_id;

  public Animal(String name, String sex, String doa, String type, String breed) {
    this.name = name;
    this.sex = sex;
    this.doa = doa;
    this.type = type;
    this.breed = breed;
    owner_id = 0;

  }

  public String getName(){
    return name;
  }

  public String getSex(){
    return sex;
  }

  public String getDoa(){
    return doa;
  }

  public String getType(){
    return type;
  }

  public String getBreed(){
    return breed;
  }

  public int getOwnerId(){
    return owner_id;
  }
  public int getId(){
    return id;
  }
  public void assignOwner(int ownerId){
    this.owner_id = ownerId;
    try(Connection con =  DB.sql2o.open()) {
      String sql = "UPDATE animals SET owner_id = :owner_id WHERE id = :id";
      con.createQuery(sql)
      .addParameter("owner_id", owner_id)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, sex, doa, type, breed, owner_id) VALUES (:name, :sex, CAST(:doa as TIMESTAMP), :type, :breed, :owner_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("sex", this.sex)
      .addParameter("doa", this.doa)
      .addParameter("type", this.type)
      .addParameter("breed", this.breed)
      .addParameter("owner_id", this.owner_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Animal> all() {
    String sql = "SELECT * FROM animals";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Animal.class);
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM animals where id=:id";
      Animal animal = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }


  @Override
  public boolean equals(Object otherAnimal) {
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }

}

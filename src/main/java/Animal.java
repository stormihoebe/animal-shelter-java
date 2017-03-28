import org.sql2o.*;
import java.util.List;
import java.time.LocalDateTime;


public class Animal {
  private String name;
  private String sex;
  private LocalDateTime doa;
  private String type;
  private String breed;
  private int owner_id;

  public Animal(String name, String sex, String type, String breed) {
    this.name = name;
    this.sex = sex;
    doa = LocalDateTime.now();
    this.type = type;
    this.breed = breed;
  }

  public String getName(){
    return name;
  }


}

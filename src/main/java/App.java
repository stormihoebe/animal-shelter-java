import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/animal-success.vtl");
    String name = request.queryParams("name");
    String sex = request.queryParams("sex");
    String doa = request.queryParams("doa");
    String type = request.queryParams("type");
    String breed = request.queryParams("breed");
    Animal newAnimal = new Animal(name, sex, doa, type, breed);
    newAnimal.save();
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/animals", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("animals", Animal.all());
    model.put("template", "templates/animals.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/animals/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
    model.put("animal", animal);
    model.put("template", "templates/animal.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  get("/customers/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/customer-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/customers", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/customer-success.vtl");
    String name = request.queryParams("name");
    String phone = request.queryParams("phone");
    String breedPref = request.queryParams("breedPref");
    String typePref = request.queryParams("typePref");
    Customer newCustomer = new Customer(name, phone, breedPref, typePref);
    newCustomer.save();
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
  }
}
// $animal.getDoa().substring(0,10)

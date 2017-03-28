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

  }
}

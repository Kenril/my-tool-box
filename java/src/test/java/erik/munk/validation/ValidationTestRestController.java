package erik.munk.validation;

import erik.munk.validation.groups.AdvancedInfo;
import erik.munk.validation.groups.BasicInfo;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationTestRestController {

  public static final String SUCCESS = "success";

  @PostMapping(value = "/testingValid")
  public String valid(@Valid UserDto communicationDTO, BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      return getJson(result);
    }
    return SUCCESS;
  }

  @PostMapping(value = "/testingBasicInfo")
  public String validateBasicInfo(@Validated(BasicInfo.class) UserDto communicationDTO, BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      return getJson(result);
    }
    return SUCCESS;
  }

  @PostMapping(value = "/testingAdvancedInfo")
  public String validateAdvancedInfo(@Validated(AdvancedInfo.class) UserDto communicationDTO, BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      return getJson(result);
    }
    return SUCCESS;
  }

  private static String getJson(BindingResult result) {
    return "{\"status\": \"error\", \"messages\":[" + result.getAllErrors().stream()
      .map( o -> "{\"" + ((FieldError) o).getField() + "\":\"" + o.getDefaultMessage() +
        "\"}").collect(Collectors.joining(
        ",")) + "]}";
  }

}

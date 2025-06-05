package erik.munk.validation;

import erik.munk.validation.groups.AdvancedInfo;
import erik.munk.validation.groups.BasicInfo;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

  @NotBlank(groups = BasicInfo.class)
  private String name;

  @NotBlank(groups = BasicInfo.class)
  private String givenName;

  @NotBlank(groups = AdvancedInfo.class)
  private String address;

}

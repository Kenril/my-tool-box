package erik.munk.mappingDesign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PersonDeserializer.class)
public class Person implements Serializable {

	private transient Integer id;

	private String name;

	private String givenName;

	private Integer age;

}

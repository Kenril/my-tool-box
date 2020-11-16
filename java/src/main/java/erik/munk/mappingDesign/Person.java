package erik.munk.mappingDesign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PersonDeserializer.class)
public class Person {

	private Integer id;

	private String name;

	private String givenName;

	private Integer age;

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Person)) return false;
		Person otherPerson = (Person) other;
		if (name != null && !name.equals(otherPerson.name)) return false;
		if (givenName != null && !givenName.equals(otherPerson.givenName)) return false;
		if (age != null && !age.equals(otherPerson.age)) return false;

		return true;
	}

}

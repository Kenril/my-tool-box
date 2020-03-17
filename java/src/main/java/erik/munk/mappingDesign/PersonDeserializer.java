package erik.munk.mappingDesign;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;

@Service
public class PersonDeserializer extends JsonDeserializer<Person> {

	private JsonKeyDao dao;

	public PersonDeserializer(JsonKeyDao dao) {
		this.dao = dao;
	}

	@SneakyThrows // Throws SQLException
	@Override
	public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		Person p = new Person();
		Field[] fields = p.getClass().getDeclaredFields();
		for (Field f : fields) {
			String fieldName = f.getName();
			String jsonKey = dao.getJsonKey(fieldName);
			String jsonValue = jsonParser.getValueAsString(jsonKey);
			f.set(p, jsonValue);
		}
		return p;
	}
}

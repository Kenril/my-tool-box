package erik.munk.mappingDesign;

import erik.munk.exception.NoUpdateException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@Profile("test")
public class PersonPortDisconnected implements PersonPort {

	private final List<Person> persons;

	public PersonPortDisconnected(List<Person> persons) {
		this.persons = persons;
	}

	public Boolean save(Person person) {
		return persons.add(person);
	}

	@Override
	public Boolean update(Person newPerson) throws NoUpdateException {
		Person oldPerson = remove(newPerson.getId());
		if (oldPerson != null && oldPerson.equals(newPerson)) {
			throw new NoUpdateException("There was no difference between the old person and the new person");
		} else {
			 return save(newPerson);
		}
	}

	@Override
	public Boolean remove(Person person) {
		return persons.remove(person);
	}

	@Override
	public Person remove(int id) {
		return persons.remove(id);
	}

	@Override
	public void clear() {
		persons.clear();
	}

	@Override
	public Person getPerson(Integer id) throws SQLException {
		Person person = persons.get(id);
		if (person == null) {
			throw new SQLException("Failed to get person for id " + id);
		}
		return person;
	}

}

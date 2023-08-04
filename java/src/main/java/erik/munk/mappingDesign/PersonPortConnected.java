package erik.munk.mappingDesign;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class PersonPortConnected implements PersonPort {

	private static final String SELECT_QUERY = "SELECT * FROM Person WHERE ? = ?";

	private final DataSource dataSource;

	public PersonPortConnected(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Boolean save(Person person) throws SQLException {
		return null;
	}

	@Override
	public Boolean update(Person person) throws SQLException {
		return null;
	}

	@Override
	public Boolean remove(Person person) throws SQLException {
		return null;
	}

	@Override
	public Person remove(int id) throws SQLException {
		return null;
	}

	@Override
	public void clear() throws SQLException {

	}

	@Override
	public Person getPerson(Integer id) throws SQLException {
		return null;
	}
}

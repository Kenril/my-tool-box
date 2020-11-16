package erik.munk.mappingDesign;

import java.sql.SQLException;

public interface PersonPort {

	Boolean save(Person person) throws SQLException;

	Boolean update(Person person) throws SQLException;

	Boolean remove(Person person) throws SQLException;

	Person remove(int id) throws SQLException;

	void clear() throws SQLException;

	Person getPerson(Integer id) throws SQLException;

}

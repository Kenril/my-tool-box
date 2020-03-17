package erik.munk.mappingDesign;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class JsonKeyDao {

	private static final String SELECT_QUERY = "SELECT * FROM JsonKey WHERE keyName = ?";

	private DataSource dataSource;

	public JsonKeyDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getJsonKey(String keyName) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
		ps.setString(1, keyName);
		ResultSet rs = ps.executeQuery();
		String result = null;
		if (rs.next()) {
			result = rs.getString("keyValue");
		}
		if (result != null) {
			return result;
		}
		throw new SQLException("Failed to get json key value for " + keyName);
	}

}

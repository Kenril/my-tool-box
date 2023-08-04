package erik.munk.database;

import erik.munk.database.model.Param;

public interface ParamDaoPort {

	public <T> Param get(String key, T defaultValue);

}

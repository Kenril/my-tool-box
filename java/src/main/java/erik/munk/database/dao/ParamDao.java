package erik.munk.database.dao;

import erik.munk.database.model.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamDao extends JpaRepository<Param, Long> {
}

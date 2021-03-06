package app.com.kenho.mapper;

import app.com.kenho.domain.Logininfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


@Repository
public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);


	int checkUnique(String username);

	Logininfo login(Logininfo logininfo);
}
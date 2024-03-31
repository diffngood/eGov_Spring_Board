package egov.main.dao;

import java.util.HashMap;

import com.lib.model.UserVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MainMapper")
public interface MainMapper {

	HashMap<String, Object> selectMain(HashMap<String, Object> paramMap) throws Exception;

	UserVO selectLogin(HashMap<String, Object> paramMap) throws Exception;

}

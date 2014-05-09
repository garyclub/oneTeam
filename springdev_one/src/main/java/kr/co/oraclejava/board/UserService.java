package kr.co.oraclejava.board;

import java.util.List;

public interface UserService {

	List<ZipcodeVO> searchZipcode(String area) throws Exception;

	void registUser(UserVO userVO) throws Exception;

	UserVO getUser(UserVO userVO) throws Exception;

}

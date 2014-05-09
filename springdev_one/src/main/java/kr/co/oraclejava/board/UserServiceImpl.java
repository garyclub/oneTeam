package kr.co.oraclejava.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<ZipcodeVO> searchZipcode(String area) throws Exception {
		return boardMapper.searchZipcode(area);
	}

	@Override
	public void registUser(UserVO userVO) throws Exception {
		boardMapper.registUser(userVO);
		
	}

	@Override
	public UserVO getUser(UserVO userVO) throws Exception{
		UserVO userInfo = boardMapper.getUser(userVO);
		if (userInfo == null){
			throw new RuntimeException("아이디 혹은 비밀번호가 틀립니다.");
		} 
		return userInfo;
	}
	
}

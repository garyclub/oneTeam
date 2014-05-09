package kr.co.oraclejava.board;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BoardMapper {

	List<ZipcodeVO> searchZipcode(String area) throws Exception;

	void registUser(UserVO userVO) throws Exception;

	@Select("SELECT user_id, user_level, user_name " +
			"FROM tab_user " +
			"WHERE user_id = #{user_id} AND user_pwd = #{user_pwd} ")
	UserVO getUser(UserVO userVO) throws Exception;

	
	void insertBoard(BoardVO boardVO) throws Exception;

	List<BoardVO> getBoardList(Pagination p) throws Exception;

	
	BoardInfoVO getBoardInfo(int bno) throws Exception;

	BoardVO getDetail(long no) throws Exception;

	int updateBoard(BoardVO boardVO) throws Exception;

	int deleteBoard(BoardVO boardVO) throws Exception;

	void insertComment(BoardCommentVO boardCommentVO) throws Exception;

	List<BoardCommentVO> getCommentList(long no) throws Exception;

	int deleteComment(BoardCommentVO boardCommentVO) throws Exception;

	BoardCommentVO getComment(BoardCommentVO boardCommentVO) throws Exception;

	int updateComment(BoardCommentVO boardCommentVO) throws Exception;
}

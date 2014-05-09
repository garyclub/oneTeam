package kr.co.oraclejava.board;

import java.util.List;

public interface BoardService {

	void insertBoard(BoardVO boardVO) throws Exception;

	List<BoardVO> getBoardList(Pagination p) throws Exception;

	BoardInfoVO getBoardInfo(int bno) throws Exception;

	BoardVO getDetail(long no) throws Exception;

	BoardVO getDetailUpCount(long no) throws Exception;

	void updateBoard(BoardVO boardVO) throws Exception;

	void deleteBoard(BoardVO boardVO) throws Exception;

	void insertComment(BoardCommentVO boardCommentVO) throws Exception;

	List<BoardCommentVO> getCommentList(long no) throws Exception;

	void deleteComment(BoardCommentVO boardCommentVO) throws Exception;

	BoardCommentVO getComment(BoardCommentVO boardCommentVO) throws Exception;

	void updateComment(BoardCommentVO boardCommentVO) throws Exception;
	
}

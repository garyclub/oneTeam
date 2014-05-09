package kr.co.oraclejava.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardMapper.insertBoard(boardVO);
		
	}

	@Override
	public List<BoardVO> getBoardList(Pagination p) throws Exception {
		return boardMapper.getBoardList(p);

	}

	@Override
	public BoardInfoVO getBoardInfo(int bno) throws Exception {
		return boardMapper.getBoardInfo(bno);
	}

	@Override
	public BoardVO getDetailUpCount(long no) throws Exception {
		BoardVO vo = boardMapper.getDetail(no);
		
		if (vo == null){
			throw new RuntimeException(no + "번 게시물이 존재하지 않습니다.");
		}
		return vo; 
	}

	@Override
	public BoardVO getDetail(long no) throws Exception {
		BoardVO vo = boardMapper.getDetail(no);
		
		if (vo == null){
			throw new RuntimeException(no + "번 게시물이 존재하지 않습니다.");
		}
		return vo; 
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		if (boardMapper.updateBoard(boardVO) == 0){
			throw new RuntimeException(boardVO.getNo()+"번 게시물을 수정할 수 없습니다.");
		}
	}

	@Override
	public void deleteBoard(BoardVO boardVO) throws Exception {
		if (boardMapper.deleteBoard(boardVO) == 0){
			throw new RuntimeException(boardVO.getNo()+"번 게시물을 삭제할 수 없습니다.");
		}
	}

	@Override
	public void insertComment(BoardCommentVO boardCommentVO) throws Exception {
		boardMapper.insertComment(boardCommentVO);
	}

	@Override
	public List<BoardCommentVO> getCommentList(long no) throws Exception {
		return boardMapper.getCommentList(no);
	}

	@Override
	public void deleteComment(BoardCommentVO boardCommentVO) throws Exception {
		if (boardMapper.deleteComment(boardCommentVO) == 0) {
			throw new RuntimeException(boardCommentVO.getCno() + "번 게시물이 존재하지 않습니다.");
		}
	}

	@Override
	public BoardCommentVO getComment(BoardCommentVO boardCommentVO)
			throws Exception {
		return boardMapper.getComment(boardCommentVO);
	}

	@Override
	public void updateComment(BoardCommentVO boardCommentVO) throws Exception {
		if (boardMapper.updateComment(boardCommentVO) == 0){
			throw new RuntimeException(boardCommentVO.getCno()+"번 댓글을 수정할 수 없습니다.");
		}
	}
}

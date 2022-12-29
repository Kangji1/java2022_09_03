package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
   private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
   private BoardDAO bdao;
   
   public BoardServiceImpl() {
      bdao = new BoardDAOImpl();
   }

   @Override
   public int register(BoardVO bvo) {
      return bdao.insert(bvo);
   }

   @Override
   public List<BoardVO> getList() {
      return bdao.selectList();
   }

   @Override
   public BoardVO getDetail(int bno) {
      return bdao.selectOne(bno);
   }

   @Override
   public int update(BoardVO bvo) {
      return bdao.update(bvo);
   }

   @Override
   public int cntUp(int bno) {
      return bdao.cntUp(bno);
   }

   @Override
   public int remove(int bno) {
	   CommentServiceImpl csv = new CommentServiceImpl();
	   
	   int isOk = csv.removeAll(bno);
      return  bdao.delete(bno);
    		 
   }

@Override
public int getPageCnt() {
	return bdao.selectCount();
}

@Override
public List<BoardVO> getListPage(PagingVO pgvo) {
	return bdao.selectList(pgvo);
}

@Override
public String getFileName(int bno) {
	return bdao.selectImageFile(bno);
}

}
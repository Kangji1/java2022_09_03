package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

   int insert(BoardVO bvo);

   List<BoardVO> selectList();

   BoardVO selectOne(int bno);

   int update(BoardVO bvo);

   int cntUp(int bno);

   int delete(int bno);

int selectCount();

List<BoardVO> selectList(PagingVO pgvo);

String selectImageFile(int bno);





}
package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

   int register(BoardVO boardVO);

   List<BoardVO> getList();

   BoardVO getDetail(int bno);

   int update(BoardVO bvo);

   int cntUp(int bno);

   int remove(int bno);

int getPageCnt();

List<BoardVO> getListPage(PagingVO pgvo);

String getFileName(int bno);

}
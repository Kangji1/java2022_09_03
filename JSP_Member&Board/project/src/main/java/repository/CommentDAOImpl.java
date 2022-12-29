package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
   
   private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
   private SqlSession sql;
   private String NS = "CommentMapper.";
   private int isOk;
   
   public CommentDAOImpl() {
      new DatabaseBuilder();
      sql = DatabaseBuilder.getFactory().openSession();
   }

   @Override
   public int insert(CommentVO cvo) {
      log.info(">>> comment > post > check3");
      isOk = sql.insert(NS+"add", cvo);
      if(isOk > 0) sql.commit();
      return isOk;
   }

   @Override
   public List<CommentVO> selectList(int bno) {
      return sql.selectList(NS+"list", bno);
   }

   @Override
   public int delete(int cno) {
      isOk = sql.delete(NS+"delete",cno);
      if(isOk > 0) sql.commit();
      return isOk;
   }

   @Override
   public int update(CommentVO cvo) {
      isOk = sql.update(NS+"modify", cvo);
      if(isOk > 0) sql.commit();
      return isOk;
   }

	@Override
	public int comDelete(int bno) {
		isOk = sql.delete(NS+"coDelete",bno);
		if(isOk > 0) sql.commit();
		return isOk;
}

}
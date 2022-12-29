package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final Logger log = LoggerFactory.getLogger(CommentController.class);
   private CommentService csv;
   private int isOk;
       
 
    public CommentController() {
       csv = new CommentServiceImpl();
    }

   
   protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");
      res.setCharacterEncoding("utf-8");
//      res.setContentType("text/html; charset=UTF-8"); jsp로 보낼게 아니기 때문에 필요 없음
      
      String uri = req.getRequestURI(); // /cmt/list/10
      String pathUri = uri.substring("/cmt/".length()); // "/cmt/" 길이의 뒤쪽 가져와! => list/10
      String path = pathUri;
      String pathVar = "";
      if(pathUri.contains("/")) {
         path = pathUri.substring(0, pathUri.lastIndexOf("/")); // list
         pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); // 10   
      }
      log.info(">>> uri >> " + uri);
      log.info(">>> pathUri >> " + pathUri);
      log.info(">>> path >> " + path);
      log.info(">>> pathVar >> " + pathVar);
      
      switch(path) {
      case "post":
         try {
            // js에서 보낸 데이터를 StringBuffer로 # 읽어들이는 작업. (내용이 많아서)
            // JSON으로 받기 때문에 req.getParameter로 받아올 수 없음
            StringBuffer sb = new StringBuffer();
            String line = null;
            
            BufferedReader br = req.getReader(); // 1. JSON으로 보낸 댓글 객체 (입력!)
            while((line = br.readLine()) != null) { // 값이 null이 아니라면 (값이 남아 있다면)
               sb.append(line); // 2. sb에 담음
            }
            // sb에 담은 line 출력
            log.info(">>> sb : " + sb.toString()); // StringBuffer이기 때문에 toString 해줘야 함
            
            // 객체로 생성 (jsonObj)
            // import org.json.simple.parser.JSONParser;
            JSONParser parser = new JSONParser(); // JSON을 객체 형태로 변환
            // key, value 형식
            JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); // value가 일반 object로 들어오기 때문에 JSONObject로 형변환
            
            int bno = Integer.parseInt(jsonObj.get("bno").toString());
            String writer = jsonObj.get("writer").toString();
            String content = jsonObj.get("content").toString();
            isOk = csv.post(new CommentVO(bno, writer, content));
            log.info(isOk > 0 ? "댓글등록성공" : "댓글등록실패");
            
            // setAttribute 대신 사용
            // isOk 결과값을 화면에 전달해줘~! - # 뿌리는 것
            PrintWriter out = res.getWriter();
            out.print(isOk); // println 으로 쓰지 않도록 주의
         } catch (Exception e) {
            log.info(">>> Comment > post > error");
            e.printStackTrace();
         }
         break;
         
      case "list":
         try {
            List<CommentVO> list = csv.getList(Integer.parseInt(pathVar));
            log.info(">>> Comment > list > DB에서 가져오기 OK");
            // JSON 형태로 변환
            JSONObject jsonObjArr = new JSONObject();
            JSONArray jsonObjList = new JSONArray();
            
            for(int i=0; i<list.size(); i++) {
               jsonObjArr = new JSONObject(); // key:value 형태
               jsonObjArr.put("cno", list.get(i).getCno()); // cno : 1
               jsonObjArr.put("bno", list.get(i).getBno()); // bno : 118
               jsonObjArr.put("writer", list.get(i).getWriter());
               jsonObjArr.put("content", list.get(i).getContent());
               jsonObjArr.put("reg_at", list.get(i).getReg_at());
               
               jsonObjList.add(jsonObjArr);
            }
            String jsonData = jsonObjList.toJSONString();
            
            PrintWriter out = res.getWriter();
            out.print(jsonData);
            
         } catch (Exception e) {
            log.info(">>> Comment > list > error");
            e.printStackTrace();
         }
         break;
         
      case "remove":
         try {
            isOk = csv.remove(Integer.parseInt(pathVar));
            log.info(">>> Comment > remove > OK");
            PrintWriter out = res.getWriter();
            out.print(isOk);
         } catch (Exception e) {
            log.info(">>> Comment > remove > error");
            e.printStackTrace();
         }
         break;
         
      case "modify":
         try {
            StringBuffer sb = new StringBuffer();
            String line = null;
            
            BufferedReader br = req.getReader(); // 1. JSON으로 보낸 댓글 객체 (입력!)
            while((line = br.readLine()) != null) { // 값이 null이 아니라면 (값이 남아 있다면)
               sb.append(line); // 2. sb에 담음
            }
            
            JSONParser parser = new JSONParser(); // JSON을 객체 형태로 변환
            JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
            
            int cno = Integer.parseInt(jsonObj.get("cno").toString());
            String content = jsonObj.get("content").toString();
            isOk = csv.modify(new CommentVO(cno, content));
            PrintWriter out = res.getWriter();
            out.print(isOk);
         } catch (Exception e) {
            log.info(">>> Comment > modify > error");
            e.printStackTrace();
         }
         break;
         
      case "delete":
         break;
      
      }
   }

   
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      service(req, res);
   }

   
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      service(req, res);
   }

}
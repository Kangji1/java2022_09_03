async function getCommentListFromServer(bno){ // 서버(컨트롤러)에 해당 bno 리스트를 달라고 요청
   try{
      const resp = await fetch('/cmt/list/' + bno); // /cmt/list/118
      const cmtList = await resp.json();
      return cmtList;
   }catch(error){
      console.log(error);
   }
}

function spreadCommentList(result){ // 댓글 list
   console.log(result);
   let div = document.getElementById("accordionExample");
   div.innerHTML = '';
   for(let i=0; i<result.length; i++){
      let html = '<div class="accordion-item">';
      html += `<h2 class="accordion-header" id="heading${i}">`;
      html += `<button class="accordion-button" type="button" data-bs-toggle="collapse"`;
      html += `data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
      html += `${result[i].cno}, ${result[i].bno}, ${result[i].writer}</button></h2>`;
      html += `<div id="collapse${i}" class="accordion-collapse collapse show"`;
      html += `aria-labelledby="heading{i}" data-bs-parent="#accordionExample">`;
      html += `<div class="accordion-body">`;
      /* 버튼 */
      html += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-warning cmtModBtn">%</button>`;
      html += `<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-danger cmtDelBtn">X</button>`;
      /* 댓글 내용 / 버튼: %(수정), X(삭제) */
      html += `<input type="text" class="form-control" id="cmtText" value="${result[i].content}"`;
      html += `${result[i].reg_at}`;
      html += `</div></div></div>`;
      /* 누적해서 담기 */
      div.innerHTML += html;
         
   }
}


async function removeCommentFromServer(cnoVal){
   try{
      const url = '/cmt/remove/'+cnoVal;
      const config = {
         method: 'post'
      }
      const resp = await fetch(url, config);
      const result = resp.text();
      return result;
      
   }catch(error){
      console.log(error);
   }
}


async function updateCommentFromServer(cnoVal, cmtTextInput){
   try{
      const url = '/cmt/modify'; /* cnoVal, cmtTextInput 두개 보내니까 body에 담아줌 */
      const config = {
         method: 'post',
         headers: {
            'Content-Type': 'application/json; charset=utf-8'
         },
         body: JSON.stringify({cno:cnoVal, content:cmtTextInput})
      }
      const resp = await fetch(url, config);
      const result = await resp.text();
      return result;
   }catch(error){
      console.log(error);
   }
}

document.addEventListener('click',(e)=>{
   
   /* 댓글 삭제 */
   if(e.target.classList.contains('cmtDelBtn')){
      let cnoVal = e.target.dataset.cno;
      console.log(cnoVal);
      removeCommentFromServer(cnoVal).then(result=>{
         if(result > 0){
            alert("댓글삭제 성공!!!");
            printCommentList(bnoVal);
            console.log(bnoVal);
         }
      })
   }
   /* 댓글 수정 */
   if(e.target.classList.contains('cmtModBtn')){
      let cnoVal = e.target.dataset.cno;
      console.log(cnoVal);
      /* e.target : 이벤트(클릭이겠죠)의 대상 */
      let div = e.target.closest('div'); // 수정버튼 에서 가장 가까운 div 태그를 선택! target 을 기준으로 가장 가까운 div 찾기
      /* div.querySelector("").value
         div : 클릭한 수정 버튼에서 가장 가까운 div태그 안에
         querySelector(#"  ") : #'아이디'를 가지고 있는 애
         value : 의 값 */
      let cmtTextInput = div.querySelector('#cmtText').value;
      updateCommentFromServer(cnoVal, cmtTextInput).then(result => {
         if(result > 0){
            alert("댓글수정 성공!!!");
            printCommentList(bnoVal);
         }
      })
   }
})

function printCommentList(bno){
   getCommentListFromServer(bno).then(result => { // result == cmtList
      console.log(result);
      if(result.length > 0){
         // 화면에 뿌리는 실제 로직을 호출
         spreadCommentList(result);
      } else {
         let div = document.getElementById("accordionExample");
         div.innerText = "comment 가 없습니다.";
      }
   })
}

async function postCommentToServer(cmtData){
   try{
      /* 내가 가고자 하는 경로 (8~15) */
      const url ="/cmt/post";
      const config={
         method:'post',
         headers:{
            'Content-Type':'application/json; charset=utf-8' // JSON으로 할 때 꼭 써줘야 함
            // application/x-www-form-urlencoded; charset=UTF-8 // 쿼리스트링 방식(기본)
         },
         body: JSON.stringify(cmtData) // JSON을 string 형태로 변환
      };
      const resp = await fetch(url, config); // url에 config 담아서 보내기
      const result = await resp.text(); // out.print(isOk) //  text 형태로 결과 받아와!
      return result;
   }catch(error){
      console.log(error);
   }
}

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
   const cmtTextInput = document.getElementById('cmtTextInput').value;
   console.log(cmtTextInput);
   /* 댓글이 입력 안 된 상황 */
   if(cmtTextInput == null || cmtTextInput==''){
      alert('댓글을 입력해주세요.');
      return false;
   }else{ /* 댓글이 입력 된 상황 */
      let cmtData = {
         bno : bnoVal,
         writer : document.getElementById('cmtWriter').value,
         content : cmtTextInput
      };

      postCommentToServer(cmtData).then(result => {
         if(result > 0){
            alert("댓글등록 성공!!!");
            document.getElementById('cmtTextInput').value = ""; // 댓글창 내용 지워주기
         }
         printCommentList(cmtData.bno);
      })

   }

})
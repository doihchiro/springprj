<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
    	$("button").on("click", function(e){
    		var formData = $("#frm");
    		var btn = $(this).data("btn");
    		
    		if (btn == 'reply') {
    			formData.attr("action", "${cpath}/board/reply");			
			} else if (btn == 'list') {
				var formData1 = $("#frm1");
				formData1.attr("action", "${cpath}/board/list");
				formData1.submit();
				return;
			} else if (btn == "reset") {
				formData[0].reset();
				return;
			}
    		
    		formData.submit();
    	});    	
    });
  </script>
</head>
<body>
 
 <div class="container">
  <h2>Spring MVC</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body">
        <form id="frm" method="post">
          
          <!-- 페이지 유지 추가 -->
          <input type="hidden" name="page" value="${cri.page}">
		  <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		  
		  <!-- type과 keyword 유지 추가 -->
		  <input type="hidden" name="type" value="${cri.type}">
		  <input type="hidden" name="keyword" value="${cri.keyword}">
          
          <!--  idx(원글,부모글) -->
          <input type="hidden" name="idx" value="${vo.idx}"/>
          <input type="hidden" name="memID" value="${mvo.memID}"/>
          <div class="form-group">
             <label>제목</label>
             <input type="text" name="title" class="form-control" value="<c:out value='${vo.title}'/>">
          </div>
          <div class="form-group">
             <label>답변</label>
             <textarea rows="10" name="content" class="form-control"></textarea>
          </div>
          <div class="form-group">
             <label>작성자</label>
             <input type="text" readonly="readonly" name="writer" class="form-control" value="${mvo.memName}">
          </div>
          <button type="button" data-btn="reply" class="btn btn-default btn-sm">답변</button>
          <button type="button" data-btn="reset" class="btn btn-default btn-sm">취소</button>
          <button type="button" data-btn="list" class="btn btn-default btn-sm">목록</button>
       </form>
       
       <!-- 페이지 유지 추가 -->
       <!-- list 페이지로 이동 -->
       <form id="frm1" method="get">
       	  <input type="hidden" name="page" value="${cri.page}">
		  <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
		  <input type="hidden" name="type" value="${cri.type}">
		  <input type="hidden" name="keyword" value="${cri.keyword}">
       </form>
    </div>
    <div class="panel-footer">스프2탄(답변형 게시판 만들기)</div>
  </div>
 </div>
</body>
</html>
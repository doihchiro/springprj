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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="${cpath}/resources/css/style.css">
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
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
 

  
  <div class="card">
    <div class="card-header">
    	<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1>Wonderwall</h1>
		    <p>Today is gonna be the day that they're gonna throw it back to you</p>
		  </div>
		</div>
    </div>
    <div class="card-body">
		<div class="row">
		  <div class="col-lg-2">
		  	<jsp:include page="left.jsp"/>
		  </div>
		  <div class="col-lg-7">
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
	          <button type="button" data-btn="reply" class="btn btn-secondary btn-sm">답변</button>
	          <button type="button" data-btn="reset" class="btn btn-secondary btn-sm">취소</button>
	          <button type="button" data-btn="list" class="btn btn-secondary btn-sm">목록</button>
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
		  <div class="col-lg-3">
		  	<jsp:include page="right.jsp"/>
		  </div>
		</div>
    </div> 
    <div class="card-footer">Big Brother Recordings Ltd.</div>
  </div>


</body>
</html>
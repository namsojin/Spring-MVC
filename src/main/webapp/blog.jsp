<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Editorial by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<strong>상세 글보기</strong> 
				</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<h3>
								글 제목<br /> ${data.title}
							</h3>
							<h3>
								글 내용<br /> ${data.content}
							</h3>
							<h3>
								글 작성자<br /> ${data.writer}
							</h3>
						</header>
						<!-- 좋아요 -->
						<c:choose>
							<c:when test="${not empty isMylike }">
											좋아요<img id="heartIng" alt="좋아요상태"
									src="images/icon-heart2.png"
									style="width: 30px; height: 30px; cursor: pointer;">
							</c:when>
							<c:otherwise>
											좋아요<img id="heartNot" alt="좋아요안한상태"
									src="images/icon-heart1.png"
									style="width: 30px; height: 30px; cursor: pointer;" >
							</c:otherwise>
						</c:choose>

						<c:if test="${data.writer == member.mid}">
							<ul class="actions">
								<li><a href="update.do" class="button big">글수정하기</a></li>
							</ul>
							<ul class="actions">
								<li><a href="delete.do?bid=${data.bid}" class="button big">글삭제하기</a></li>
							</ul>
						</c:if>
						<ul class="actions">
							<li><a href="main.do" class="button big">메인가기</a></li>
						</ul>
						<ul class="actions">
							<li><a href="logout.do" class="button big">로그아웃</a></li>
						</ul>
					</div>
					
					<span class="image object"> <img
						src="images/${data.fileName}" alt="" />
					</span>
				</section>



			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.6.3.min.js">
	</script>
	
	
	<!-- 좋아요 추가 JS -->
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#heartNot").on("click",function(){
			console.log('버튼 클릭함');
			
			var data={
					bid:'${data.bid}',	
					mid:'${member.mid}'
				};
			
			$.ajax({
				
				url: 'heart.do',
				type:'POST',
				contentType : 'application/json; charset=utf-8',
	        	data : JSON.stringify(data),
	        	success : function(result) {
	                console.log(result);
	                if (result == "success") {
	                    console.log("좋아요 성공!");
	                }
	           
			 	},
				 error : function(e) {
	                console.log(e);
	                alert('찜할 수 없습니다.');
	                location.reload(); // 실패시 새로고침하기	
				 }
			});
			
		});
		
		
		
		
		
		
		
	});
	
	</script>
	
	
	
	
	
	
	
	
	
	
							
</body>
</html>
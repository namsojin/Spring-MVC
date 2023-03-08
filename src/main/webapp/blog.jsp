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
					<h5>상세 글보기</h5> 
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
						<!-- 공유하기 -->
						<a href="javascript:kakaoShare()" class="icon brands fa-dribbble"><span class="label">공유하기</span></a>
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
	
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"
		integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
		crossorigin="anonymous">
	</script>
	
	<!-- 좋아요 버튼 JS -->
	<script type="text/javascript">
	$(document).ready(function(){
		
		//좋아요 추가 JS
		$("#heartNot").on("click",function(){
			
			//로그인을 안 한 상태였을 시
			if ("${member.mid}" == "") {
                if (confirm("로그인 한 회원만 이용가능합니다. 로그인 하시겠습니까?")) {
                    // 승낙하면 로그인 페이지로 이동
                    location.href = '${pageContext.request.contextPath}/login.do';
                } else {
                    // 거부하면 해당 페이지 새로고침
                    location.reload();
                }
            // 로그인 상태시 찜하기 버튼을 누르면    
            } else {
			
			var bid = '${data.bid}';
			var mid = '${member.mid}';
			console.log('bid:'+bid+"/mid:"+mid);
			
			var data = {
				bid : bid,	
				mid : mid	
			};
	
			$.ajax({
				
				url: '${pageContext.request.contextPath}/heart.do',
				type:'POST',
				contentType : 'application/json; charset=utf-8',
	        	data :JSON.stringify(data),
	        	success : function(resp) {
	                if (resp == 'success') {
	                    console.log("좋아요 성공!");
	                    if (confirm("해당 상품을 찜하셨습니다. 찜목록 페이지로 이동하시겠습니까?")) {
                            // 승낙하면 마이페이지의 찜하기 리스트로 이동
                            location.href = '${pageContext.request.contextPath}/main.do';
                        } else {
                            // 거부하면 해당 페이지 새로고침하여 찜한거 반영되게하기(HTTP의 속성 때문)
                            location.reload();
                        }
	                }
	                else{
	                	console.log('좋아요 실패!');
	                	alert('좋아요 할 수 없습니다.관리자에게 문의 해주세요.');
	                	location.reload();
	                }
	           
			 	},
				 error : function(e) {
					console.log('오류발생')
	                console.log(e);
	                alert('좋아요 할 수 없습니다.관리자에게 문의 해주세요.');
	                location.reload(); // 실패시 새로고침하기	
				 }
			});
		  
            }	
		});
		
		//좋아요 취소 JS
		$("#heartIng").on("click",function(){
			console.log('취소할거니');
		   
			var mynum='${isMylike.mynum}';
			console.log('isMylike.mynum:'+mynum);
			
			var data = {
				mynum : mynum
			};
	
			$.ajax({
				
				url: '${pageContext.request.contextPath}/heartNo.do',
				type:'POST',
				contentType : 'application/json; charset=utf-8',
	        	data :JSON.stringify(data),
	        	success : function(resp) {
	                if (resp == 'success') {
	                    console.log("취소 성공!");
	                    alert('좋아요 취소 하셨습니다.');
	                    location.reload();
	                }
	                else{
	                	console.log('좋아요 실패!');
	                	alert('좋아요 취소를 할 수 없습니다.관리자에게 문의 해주세요.');
	                	location.reload();
	                }
	           
			 	},
				 error : function(e) {
					console.log('오류발생')
	                console.log(e);
					alert('찜 취소를 할 수 없습니다.관리자에게 문의 해주세요.');
	                location.reload(); // 실패시 새로고침하기	
				 }
			});
			
		});
		
	});
	
	</script>
	
	<!-- 카카오 공유하기 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript">
		// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('3d08dd963da5d6d0d9d5aed059ea3d21');

		// SDK 초기화 여부를 판단합니다.
		console.log(Kakao.isInitialized());

		function kakaoShare() {
			Kakao.Link.sendDefault({
				objectType : 'feed',
				content : {
					title : 'START TO UP',
					description : '스타투업 사이트입니다.',
					imageUrl : 'https://ifh.cc/g/YjnLWC.png',
					link : {
						mobileWebUrl : 	
							'http://localhost:8088/app/main.do',
						
						webUrl : 'http://localhost:8088/app/main.do',
					},
				},
				buttons : [ {
					title : '웹으로 보기',
					link : {
						mobileWebUrl : 'http://localhost:8088/app/main.do',
						webUrl : 'http://localhost:8088/app/main.do',
					},
				}, ],
				// 카카오톡 미설치 시 카카오톡 설치 경로이동
				installTalk : true,
			})
		kakaoDelete();
		}
		
		
		/*카카오 연결 끊기*/
        function kakaoDelete() { //  탈퇴 버튼 클릭시 실행될 함수
           if (Kakao.Auth.getAccessToken()) {
              console.log(Kakao.Auth.getAccessToken())
              Kakao.API.request({
                 url : '/v1/user/unlink', // --> 탈퇴시 url
                 success : function(response) {
                    console.log(response)
                    alert("탈퇴완료.");
                 },
                 fail : function(error) {
                    console.log(error)
                 },
              })
              Kakao.Auth.setAccessToken(undefined)
           }
        }
        /*  회원탈퇴 end */

	</script>











</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>

<html>
<head>

<title><spring:message code="message.login.title"/></title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
					<a href="login.do?lang=en">ENGLISH</a>
					<a href="login.do?lang=ko">한국어</a>
					
				</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<h1><spring:message code="message.login.title"/></h1>
						
						</header>
						<form method="post" action="login.do">
							<div class="row gtr-uniform">
								<div class="col-6 col-12-xsmall">
									<input type="text" name="mid" placeholder="<spring:message code="message.login.putId"/>" value="${user.mid}" required />
								</div>
								<div class="col-6 col-12-xsmall">
									<input type="password" name="mpw" placeholder="<spring:message code="message.login.putPw"/>" value="${user.mpw}"
										required />
								</div>
								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="<spring:message code="message.login.btnLogin"/>" class="button big" /></li>
									</ul>
								</div>
							</div>
						</form>
						<!-- 회원가입 -->					
						<div class="col-12">
									<ul class="actions">
										<li><a href="join.do" class="button big"><spring:message code="message.login.btnSignUp"/></a></li>
									</ul>
						</div>
						<!-- 카카오 로그인 -->
						 <a id="kakao-login-btn" href="javascript:kakaoLogin()"> 
     							 <center><img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
        									 width="222" alt="카카오 로그인 버튼" /></center>
      					</a>
      					<!-- 카카오 로그아웃 -->
      					<a href="javascript:kakaoDelete()" style="text-decoration: none;"> 
            							<center><span style="color: black;">카카오회원탈퇴</span></center>
         				</a>
					</div>
					<span class="image object"> <img src="images/yumi.jpg"
						alt="유미 이미지" />
					</span>
				</section>
	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	
	<!-- 카카오 스크립트 -->
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    
    <!-- 카카오 로그인 JS -->
    <script>
                 // SDK를 초기화 합니다. 사용할 앱 키(javascript 키) 입력합니다.
            	  window.Kakao.init('3d08dd963da5d6d0d9d5aed059ea3d21');
                  // SDK 초기화 여부를 판단합니다. true가 나온다면 정상 작동
                  console.log(Kakao.isInitialized());
                 
                   // 카카오 로그인 함수 생성
                    function kakaoLogin() {
                      window.Kakao.Auth.loginForm({
                        scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                        success: function (authObj) {
                          // console.log(authObj); // 로그인 성공하면 받아오는 데이터
                          
                          
                          window.Kakao.API.request({
                            // 사용자 정보 가져오기
                      		url: '/v2/user/me',
                         	//url: '/v1/user/unlink',
                           // 연결 끊기(회원탈퇴)
                             
                      	    // 로그인 성공시 받아올 데이터 
                            success: (response) => {
                              
                         	// var accessToken = Kakao.Auth.getAccessToken(); // 엑세스 토큰 할당
                         	// Kakao.Auth.setAccessToken(accessToken);	// 엑세스 토큰 사용하게 등록
							
                         	 //this.kakaoLogin.setToken(e.data['access_token']);
                         	  
                         	  const id = response.id;  // 로그인 성공한 유저 고유 id 발급됨
                         	  const properties = response.properties;
                              const name = properties.nickname;
                              const email = response['kakao_account']['email'];
                              console.log('email:'+email);
                              console.log('id:'+id);
                              
                          	location.href="kakaoLogin.do?mname=" + name + "&mid=" + id+"&kakao=kakao"; //리다이렉트 주소
                            },
                          });
                        },
                        fail: function (error) {
                          console.log(error);	// 실패하면 콘솔에 error 메세지
                        },
                      });
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
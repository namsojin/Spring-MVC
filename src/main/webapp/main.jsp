<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title><spring:message code="message.main.title"/></title>
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
								
									<a href="main.do?lang=en">ENGLISH</a>
					                <a href="main.do?lang=ko">한국어</a>
					                
										
					                
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li></li>
										<!-- 커스텀 태그 이용하기 -->
										<c:choose>
											<c:when test="${not empty member}">
											
											<li><a href="logout.do" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
											</c:when>
											<c:otherwise>
										<li><a href="login.do" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										</c:otherwise>
										</c:choose>
									</ul>
								</header>

							<!-- Content -->
								<section>
									<header class="main">
										<c:choose>
											<c:when test="${not empty member && not empty kakao}">
										<h1>${member.mname}<spring:message code="message.main.hello"/></h1>
											</c:when>
											<c:when test="${not empty member }">
											<h1>${member.mid}<spring:message code="message.main.hello"/></h1>
											</c:when>
											<c:otherwise>
											<h1><spring:message code="message.main.login"/></h1>
											</c:otherwise>
										</c:choose>
									</header>

									<span class="image main"><img src="images/pic.png" alt="귀여운 티모 이미지" /></span>
									<!-- 글검색 -->
									<hr class="major" />
									<h2><spring:message code="message.main.search"/></h2>
									<form action="main.do" method="post">
										<select name="searchCondition">
											<c:forEach var="v" items="${searchMap}">
											<option value="${v.value}">${v.key}</option>
											</c:forEach>
											
										</select>
										<input type="text" name="searchContent" placeholder="<spring:message code="message.main.searchContent"/>" required>
										<input type="submit" class="button primary icon solid fa-search"  value="<spring:message code="message.main.btnSearch"/>">
									</form>
									
									<!-- 글목록 -->
									<hr class="major" />
									<h2><spring:message code="message.main.boardList"/></h2>
									<ul>
										<c:forEach var="v" items="${datas}">
											<li><a href="blog.do?bid=${v.bid}">${v.bid}</a> | ${v.title} | ${v.writer} | ${v.content} |
								           </li>
										</c:forEach>
									</ul>
									
									<!-- 글추가 -->
									<hr class="major" />
									<h2>
									<spring:message code="message.main.insertBoard" />
									</h2>
									<c:choose>
										<c:when test="${not empty member}">
											<form action="write.do" method="post" enctype="multipart/form-data">
											<input type="text" name="title" placeholder="<spring:message code="message.main.putTitle"/>"required> 
											<input type="text" name="content" placeholder="<spring:message code="message.main.putContent"/>" required> 
											<input type="file" value="파일일" name="uploadFile" onchange="readURL(this);"> 
											<img id="preview" /> 
											<input type="hidden" name="writer" value="${member.mid}" required> 
											<input type="submit"class="button primary icon solid fa-search" value="<spring:message code="message.main.btnInsert"/>">
							                </form>
						               </c:when>
						               <c:otherwise>
						               <h4>로그인 후 이용 가능합니다.</h4>
						               </c:otherwise>
					                </c:choose>

					                <!-- 좋아요 목록 -->
									<hr class="major" />
									<h2>좋아요 목록</h2>
									<c:choose>
										<c:when test="${not empty member}">
											<ul>
												<c:forEach var="v" items="${mylikeList}">
													<li><a href="blog.do?bid=${v.bid}">${v.bid}</a> | ${v.mynum} |<a href="delheart.do?mynum=${v.mynum }" class="icon brands fa-snapchat-ghost"></a>
								           			</li>
												</c:forEach>
											</ul>
										</c:when>
										<c:otherwise>
											 <h4>로그인 후 이용 가능합니다.</h4>
										</c:otherwise>
									</c:choose>
									<hr class="major" />

									<h2>Magna etiam veroeros</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis dapibus rutrum facilisis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Etiam tristique libero eu nibh porttitor fermentum. Nullam venenatis erat id vehicula viverra. Nunc ultrices eros ut ultricies condimentum. Mauris risus lacus, blandit sit amet venenatis non, bibendum vitae dolor. Nunc lorem mauris, fringilla in aliquam at, euismod in lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In non lorem sit amet elit placerat maximus. Pellentesque aliquam maximus risus, vel sed vehicula.</p>
									<p>Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque venenatis dolor imperdiet dolor mattis sagittis. Praesent rutrum sem diam, vitae egestas enim auctor sit amet. Pellentesque leo mauris, consectetur id ipsum sit amet, fersapien risus, commodo eget turpis at, elementum convallis elit. Pellentesque enim turpis, hendrerit tristique lorem ipsum dolor.</p>

									<hr class="major" />

									<h2>Lorem aliquam bibendum</h2>
									<p>Donec eget ex magna. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque venenatis dolor imperdiet dolor mattis sagittis. Praesent rutrum sem diam, vitae egestas enim auctor sit amet. Pellentesque leo mauris, consectetur id ipsum sit amet, fergiat. Pellentesque in mi eu massa lacinia malesuada et a elit. Donec urna ex, lacinia in purus ac, pretium pulvinar mauris. Curabitur sapien risus, commodo eget turpis at, elementum convallis elit. Pellentesque enim turpis, hendrerit.</p>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis dapibus rutrum facilisis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Etiam tristique libero eu nibh porttitor fermentum. Nullam venenatis erat id vehicula viverra. Nunc ultrices eros ut ultricies condimentum. Mauris risus lacus, blandit sit amet venenatis non, bibendum vitae dolor. Nunc lorem mauris, fringilla in aliquam at, euismod in lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In non lorem sit amet elit placerat maximus. Pellentesque aliquam maximus risus, vel sed vehicula.</p>

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
			
			
			
			
			
			
			<!-- 미리보기 -->
			<script type="text/javascript">
			function readURL(input) {
				
				  let img = document.getElementById('preview');
				  if (input.files && input.files[0]) {
				    var reader = new FileReader();
				    reader.onload = function(e) {
				     img.src = e.target.result;
				     img.width = 250;
				     img.height = 250;
				    };
				    reader.readAsDataURL(input.files[0]);
				  } else {
				    document.getElementById('preview').src = "";
				  }
				}

			
			</script>
			
			
			<script src="https://code.jquery.com/jquery-3.6.3.min.js"
				integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
				crossorigin="anonymous">
			</script>


	</body>
</html>
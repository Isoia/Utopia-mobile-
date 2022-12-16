<%@page import="dto.PostListDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Utopia</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/index.css" rel="stylesheet" />
    </head>
    <body id="page-top">
       	<jsp:include page="./module/nav.jsp" />
		<jsp:useBean class="dao.PostDAO" id="postDAO" scope="session"></jsp:useBean>
		
	    <%
	    	request.setCharacterEncoding("utf-8");
	    
	    	session.getAttribute("searchlist");
			String id = (String)session.getAttribute("id");
	    	Vector<PostListDTO> monthBestPosts = postDAO.getMonthBestList();
	    	Vector<PostListDTO> newPosts = postDAO.getNewList();
	    %>
        <!-- Search -->
        <section class="page-section comunity-main-search" id="search">
			<div id="tag-container" class="container">
				<div id="search-container" class="tag-container-item">
					<form action="postlist.do" method="get">
						<div id="search-input-container">
							<input id="search-input" type="text" name="searchword">
							<input type="hidden" name="btn" value="new">
							<input type="hidden" name="page" value="1">
							<input type="image" src="assets/img/origin/search-icon.png">
						</div>
					</form>
				</div>
			</div>
		</section>
		
		<!-- Best article -->
        <section class="page-section" id="best-article">
            <div class="container best-container">
                <div class="text-center">
                    <h3 class="section-heading text-uppercase">이달의 베스트 밥상</h3>
                    <hr>
                </div>
                <div class="row text-center">
                <!-- 베스트 게시물 시작 -->
                <% for(PostListDTO dto : monthBestPosts) { %>
                    <jsp:include page="module/post_best.jsp">
						<jsp:param value="<%= dto.getMainimage() %>" name="mainImage"/>
						<jsp:param value="<%= dto.getPostnum() %>" name="postNum"/>
						<jsp:param value="<%= dto.getUsername() %>" name="userName"/>
						<jsp:param value="<%= dto.getTitle() %>" name="title"/>
					</jsp:include>
				<% } %>
                <!-- 베스트 게시물 끝 -->
                </div>
            </div>
        </section>
        
        <!-- Community Grid-->
        <section class="page-section" id="newest">
            <div class="container comu-container">
                <div class="text-center">
                    <h3 class="section-heading text-uppercase">새로 나온 레시피</h3>
                    <hr>
                </div>
                <div class="col">
                   <!-- 신규 레시피 시작 -->
                <%
               		for(PostListDTO dto : newPosts){
                %>
                   <!-- 게시물 시작 -->
                   <jsp:include page="module/post_list.jsp">
						<jsp:param value="<%= dto.getMainimage() %>" name="mainImage"/>
						<jsp:param value="<%= dto.getPostnum() %>" name="postNum"/>
						<jsp:param value="<%= dto.getUsername() %>" name="userName"/>
						<jsp:param value="<%= dto.getTitle() %>" name="title"/>
						<jsp:param value="<%= dto.getViewscount() %>" name="viewsCount"/>
					</jsp:include>
					
			    <% } %> 
                <!-- 신규 레시피 끝 -->
                </div>
            </div>
        </section>
       
        
        <jsp:include page="./module/footer.jsp" />
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

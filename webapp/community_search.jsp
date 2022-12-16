<%@page import="dao.PostDAO"%>
<%@page import="dto.PostListDTO"%>
<%@page import="java.util.Vector"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<%
			request.setCharacterEncoding("utf-8");

			PostDAO dao = new PostDAO();
			Vector<PostListDTO> posts = (Vector<PostListDTO>)session.getAttribute("posts");
			int pageCount = (int)session.getAttribute("count");
			
			int curpage = Integer.parseInt(request.getParameter("page"));
			
			int start = (curpage - 4) >= 1 ? curpage - 4 : 1;
			int end = (curpage + 4) <= pageCount ? curpage + 4 : pageCount;
			
			Vector<String> tags;
		%>

        <!-- Search -->
        <section class="page-section py-3 comu-search-section1" id="search">
			<div id="tag-container" class="section-container">
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

        <section class="page-section py-3 comu-search-section2" id="list">
            <div class="container comu-search-maindiv">
                <div class="comu-search-threebtn">
                    <a class="btn btn-primary px-3" href="postlist.do?page=<%= pageCount %>&searchword=${searchword}&btn=new">최신순</a>
                    <a class="btn btn-primary px-3" href="postlist.do?page=<%= pageCount %>&searchword=${searchword}&btn=rec">댓글순</a>
                    <a class="btn btn-primary px-3" href="postlist.do?page=<%= pageCount %>&searchword=${searchword}&btn=vie">조회순</a>
                    <hr class="mt-3">
                </div>
                
                <div id="post-list-container" class="col comu-container">
                <%
                	if(posts.isEmpty()) {
                		out.println("<h6 class=\"my-3\">관련된 게시물이 존재하지 않습니다</h6>");
                	}
                	else {
                		for(PostListDTO dto : posts) { %>
                   		
						<jsp:include page="module/post_list.jsp">
							<jsp:param value="<%= dto.getMainimage() %>" name="mainImage"/>
							<jsp:param value="<%= dto.getPostnum() %>" name="postNum"/>
							<jsp:param value="<%= dto.getUsername() %>" name="userName"/>
							<jsp:param value="<%= dto.getTitle() %>" name="title"/>
							<jsp:param value="<%= dto.getViewscount() %>" name="viewsCount"/>
						</jsp:include>                   		
                   		
			    	<% } %>
			    	<ul class="page-btn my-3 text-center">
			    		<li><a href="postlist.do?page=1&searchword=${searchword}&btn=${btn}"> &lt;&lt; </a></li>
			    		<% for(int i = start; i <= end; i++) { %>
			    			<% if(i == curpage) { %>
			    				<li class="selected"><a class="b" href="postlist.do?page=<%= i %>&searchword=${searchword}&btn=${btn}"><%= i %></a></li>
			    			<% } else { %>
			    				<li><a class="b" href="postlist.do?page=<%= i %>&searchword=${searchword}&btn=${btn}"><%= i %></a></li>
			    			<% } %>
			    		<% } %>
			    		<li><a href="postlist.do?page=<%= pageCount %>&searchword=${searchword}&btn=${btn}"> &gt;&gt; </a></li>
			    	</ul>
               	<% } %> 
              
                </div>
            </div>
        </section>
        
        <jsp:include page="./module/footer.jsp" />
        
        <script type="text/javascript">
        	
        </script>
        
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

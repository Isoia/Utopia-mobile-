<%@page import="dto.NewsDTO"%>
<%@page import="dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Utopia</title>
		<link href="css/index.css" rel="stylesheet" />
    </head>
    <body>
    	<%
    		ManagerDAO dao = new ManagerDAO();
    	
    		NewsDTO dto = dao.newsRecent();
    		
    		System.out.println(dto.getNewsContent());
    	
    		int newsId = dto.getNewsId();
    		String newsImage = dto.getNewsImage();
    		String[] newsTags = dto.getNewsTags().split(" ");
    		String newsContent = dto.getNewsContent();
    	%>
    	<jsp:include page="./module/nav.jsp" />
        <section class="page-section">
        	<div class="container news-container">
        		<div class="article-item news-article-item">
        			<h3>오늘의 뉴스</h3>
        		</div>
        	
        		<div class="article-item id-article-thumbnail">
        			<div class="card-wrapper id-article-thumbnail-image">
			        	<div class="image-wrapper">
			        		<img src="/Utopia/images/<%= newsImage %>" alt="<%= newsImage %>">
			        	</div>
			        </div>
        		</div>
		
				<div class="card-wrapper news-card-wrapper">
					<div class="article-item p-3">
						<%= newsContent %>
					</div>
				</div>
				
				<ul class="article-tag-list article-item article-comment">
					<%
						for(String tag: newsTags) {
					%>
						<li class="mx-1"><a class="search-tag" href="postlist.do?searchword=<%="%23"+tag %>&btn=new&page=1"> <%= tag %> </a></li>
				    <%
						}
				    %>
				</ul>
			</div>
		</section>
		
		<jsp:include page="./module/footer.jsp" />
	</body>
</html>
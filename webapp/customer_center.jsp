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
        <link href="css/index.css" rel="stylesheet" />
    </head>
    
    <style>
    #element-top{
    background-color: black;
    }
    </style>
    
    <body id="page-top">
        <jsp:include page="./module/nav.jsp" />
        
        <section class="page-section customer-center-article">
        	<div class="text-center">
        		<h2>무엇을 도와드릴까요?</h2>
        	</div>
        </section>
		
		<!-- First Element -->
		<a class ="customer-center-element" href ="module/customer-center/quary1.jsp">
	        <section class="quary-section"style="height: 300px;background-color: #ffffbf;"> 
		        <div class="flex-item" id ="flex-container1" >
		            <div class="flex-item">
		                <h2 class="pc-screen-text">태그 검색 사용법</h2>
		                <h3 class="app-screen-text">태그 검색 사용법</h3>
		                <h6>검색어, 태그</h6>
		            </div>
		            <div class="flex-item" flex: 1 auto;><img src="assets/img/community-center-img/1.png"></div>
		        </div>
	        </section>
        </a>
        
        <!-- Second Element -->
        <a class ="customer-center-element" href ="module/customer-center/quary2.jsp">
        	<section class="quary-section" style="height: 300px;background-color: #ffffff;"> 
        		<div class="flex-item" id ="flex-container2">
            		<div class="flex-item">
		                <h2 class="pc-screen-text">Utopia 계정</h2>
		                <h3 class="app-screen-text">Utopia 계정</h3>
		                <h6>분실, 인증</h6>
		            </div>
		            <div class="flex-item"><img src="assets/img/community-center-img/2.png"></div>
            	</div>
        	</section>
        </a>
        
        
        <!-- Third Element -->
        <a class ="customer-center-element" href ="module/customer-center/quary3.jsp">
	        <section class="quary-section" style="height: 300px; background-color: #cce8f4;">
				<div class="flex-item" id ="flex-container3">
					<div class="flex-item">
						<h2 class="pc-screen-text">자주 묻는 질문</h2>
						<h3 class="app-screen-text">자주 묻는 질문</h3>
						<h6>검색1, 검색2</h6>
					</div>
				<div class="flex-item"><img src="assets/img/community-center-img/3.png" ></div> 
				</div>
	        </section>
        </a>
        
        <jsp:include page="./module/footer.jsp" />
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

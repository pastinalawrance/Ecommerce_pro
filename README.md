<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Shopping cart!</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
<div class="card-header my-3">All Products</div>
<div class="row">

<%
if( !products.isEmpty()){
	for(Product p : products) {
	%>
		<div class="col-md-3 my-3">
		<div class="card w-100">
		  <img class="card-img-top" src="product-images/<%= p.getImage() %>" alt="Card image cap">
		  <div class="card-body">
		    <h5 class="card-title"> <%= p.getName() %></h5>
		    <h6 class="price">price: $<%= p.getPrice() %></h6>
		    <h6 class="category">Category:<%= p.getCategory() %></h6>
		    <div class="mt-3 d-flex justify-content-between">
		    <a class="btn btn-dark" href="add-to-cart?id=<%= p.getId() %>">Add to Cart</a>
		    <a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
		    </div>
		    
		  </div>
		</div>
		</div>
		
	<%
	}
}else {
	out.println ("There is no products");
}

%>


</div>
</div>


<%@include file="includes/footer.jsp" %>

</body>
</html>

package pastipack.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import pastipack.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet(name= "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ArrayList<Cart> cartlist;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cart  cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartlist.add(cm);
				session.setAttribute("cart-list", cartlist);
				response.sendRedirect("index.jsp");
			}else {
				cartlist = cart_list;
				boolean exist = false;
				for(Cart c : cart_list) {
					if(c.getId() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align:center'>Item already existb in cart.<a href='cart.jsp'>Go to cart page </a></h3>");
					}
					}
					if(!exist) {
						cartlist.add(cm);
						response.sendRedirect("index.jsp");
					}
			
				 }
			
				
			}
			}
		}
	



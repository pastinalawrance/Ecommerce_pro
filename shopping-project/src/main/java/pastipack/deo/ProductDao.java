package pastipack.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import pastipack.model.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Product> getAllProducts() {
		List<Product> book = new ArrayList<>();
		
		
		try {
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				book.add(row);
			}
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return book;
	}
		
		public Product getSingleProduct (int id) {
			 Product row = null;
			 try {
				 query = "select * from  products where id=?";
				 pst = this.con.prepareStatement(query);
				 pst.setInt(1, id);
				 ResultSet rs = pst.executeQuery();
				 
				 while(rs.next()) {
					 row = new Product();
					 row.setId(rs.getInt(id));
					 row.setName(rs.getString("name"));
					 row.setCategory(rs.getString("category"));
					 row.setPrice(rs.getDouble("price"));
					 row.setImage(rs.getString("image"));
				 }
				 
			 }catch (Exception e) {
				 e.printStackTrace();
				 System.out.println(e.getMessage());
			 }
		return row;
		 
		
	}
		public double getTotalCartPrice(ArrayList<Cart> cartList ){
			  double sum = 0;
			  try {
				  if(cartList.size()>0) {
					  for(Cart item:cartList) {
						  query = "select price from products where id=?";
						  pst = this.con.prepareStatement(query);
						  pst.setInt(1, item.getId());
						  rs =pst.executeQuery();
						  
						  while(rs.next()) {
							  sum+=rs.getDouble("price")*item.getQuantity();
						  }
						  
					  }
				  }
				  
			  }catch(Exception e) {
				  e.printStackTrace();
				  System.out.println(e.getMessage());
			 
		  }
		return sum;
		  }
public List<Cart> getCartProduct(ArrayList<Cart>cartlist){
	List<Cart> book = new ArrayList<Cart>();

	try {
		if(cartlist.size()>0) {
			for(Cart item:cartlist) {
				query = " select * from products where id=?";
				pst = this.con.prepareStatement(query);
				pst.setInt(1, item.getId());
				rs = pst.executeQuery();
				while(rs.next()) {
					Cart row = new Cart();
					row.setId(rs.getInt("id"));
					row.setName(rs.getString("name"));
					row.setCategory(rs.getString("category"));
					row.setPrice(rs.getDouble("price")*item.getQuantity());
					row.setQuantity(item.getQuantity());
					book.add(row);
					
				}
				
				
				
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		
	}
		
	return book;	
}
 
}

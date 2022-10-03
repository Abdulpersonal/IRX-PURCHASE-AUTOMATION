package Com.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Irx_Database {
	public static void main(String[] args)
	{

	// TODO Auto-generated method stub
	
	//final String JDBC_DRIVER="com.mysql.jdbc.driver";
	final String URL="jdbc:mysql://139.59.68.85:3306/irx_merge_sep_07";
	final String UN="IdearxQARead";
	final String p="IdearxRead@123";
	System.out.println("Started");

	
	
	


	Connection conn=null;
	Statement st=null;

	try {
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			System.out.println("Driver connection completed");

		conn=DriverManager.getConnection(URL,UN,p);
		System.out.println("DB connection completed");

		
		st=conn.createStatement();
		String sql="SELECT P.SEQUENCEID AS PO,\r\n"
				+ "P.Createdtime AS PODATE,\r\n"
				+ "P.TOTALAMOUNT AS POTOTALAMOUNT,\r\n"
				+ "P.SUBTOTAL AS SUBTOTAL,\r\n"
				+ "PD.BUYERITEMNAME AS ITEM,\r\n"
				+ "PD.UNITPRICE AS UNITRATE,\r\n"
				+ "PD.QUANTITY AS NOS,\r\n"
				+ "PD.TAXVALUE AS TAXPERC,\r\n"
				+ "PD.TOTALAMOUNT AS ITEMAMOUNT\r\n"
				+ "FROM `irx_purchase_order` AS P\r\n"
				+ "INNER JOIN `irx_po_detail` AS PD \r\n"
				+ "ON P.ID=PD.PURCHASEORDER\r\n"
				+ "WHERE  \r\n"
				+ "-- AND P.STATUS='Dispatched'\r\n"
				+ "   P.CREATEDTIME >= CURDATE()";
		
		ResultSet executequery=st.executeQuery(sql);
		System.out.println("Query executed");
		List<String> list=new ArrayList<String>(); 
		while(executequery.next()) {
			System.out.println("--------");
			String val1=executequery.getString("ITEM");
			String ponumber=executequery.getString("PO");
			String val3=executequery.getString("NOS");
			String val=executequery.getString("ITEMAMOUNT");
			String val2=executequery.getString("POTOTALAMOUNT");
			
			list.add(val3);
			list.add(val);
			list.add(val2);
			list.add(ponumber);
			//System.out.println(val1+":"+list);
			 String testinglist= list.get(1);
			// System.out.println(testinglist);

			 String Name=null;
			 String no=null;
			 Name=list.get(1);
			 no=list.get(2);
			 System.out.println("Po Number is "+ponumber+" and ("+val1+") poquantity "+val3+" item_amount= "+Name+" and pototal is ."+val2);
			
			list.clear();
			
			//System.out.println(val1+"-"+val+"-"+val3+"-"+val2);
			
		}			
		
		executequery.close();
		st.close();
		conn.close();
		System.out.println("--------end-----");
		
	}catch(SQLException e) {
		e.printStackTrace();	
	}
	
}
	
	
	catch(ClassNotFoundException e) {
	e.printStackTrace();
}
	//return Name;

}

}

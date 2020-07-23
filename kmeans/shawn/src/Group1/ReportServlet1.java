/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/userTableSpreadsheet")
public class ReportServlet1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {

        // create the workbook, its worksheet, and its title row
    	List<String[]> apppp=new LinkedList<String[]>();
       
        try {
            // read database rows
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM User ORDER BY Username";    
            ResultSet results = statement.executeQuery(query);
            
            
            // create the spreadsheet rows
            int i = 3;
            while (results.next()) {
                String[] appp=new String[9];
                appp[0]=""+results.getInt("UsernameID");
                appp[1]=""+(results.getString("FirstName"));
                appp[2]=""+(results.getString("LastName"));
                appp[3]=""+(results.getString("Phone"));
                appp[4]=""+(results.getString("Address"));
                appp[5]=""+(results.getString("City"));
                appp[6]=""+(results.getString("State"));
                appp[7]=""+(results.getString("Zipcode"));
                appp[8]=""+(results.getString("Email"));
                apppp.add(appp);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            this.log(e.toString());
        }

        String[][] args=new String[apppp.size()][9];
        
        for(int i=0;i<apppp.size();i++){
        	args[i]=apppp.get(i);
        }
        
        String[] columnNames=new String[]{"User Name","First Name"," Last Name","Phone","ADdress","City","State","Zip Code","Email"};
        
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");
          
        //This data needs to be written (Object[])
        int rownum = 0;
        Row row;
        Cell cell;
        int j=0;
        row=sheet.createRow(0);
        
        for(j=0;j<args[0].length;j++){
    		cell = row.createCell(j);
    		cell.setCellValue(columnNames[j]);
    	}
        
        for (int i=0;i<args.length;i++){
        	row = sheet.createRow(i+1);
        	for(j=0;j<args[0].length;j++){
        		cell = row.createCell(j);
        		cell.setCellValue(args[i][j]);
        	}
        }
        try
        {
        	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        	response.setHeader("content-disposition", 
                      "attachment; filename=users.xlsx");
              response.setHeader("cache-control", "no-cache");
        	 OutputStream out = response.getOutputStream();
             workbook.write(out);
             out.close();
            //Write the workbook in file system
            /*FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println(filename+ " written successfully on disk.");*/
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
 
       
        // set the response headers
      

        // get the output stream and send the workbook to the browser
       
    }
}
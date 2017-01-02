package class_dao;
import class_con.ConnectionFactory;

import java.sql.*;
/**
 * Created by Света on 15.12.2016.
 */
public class LoginDAO {

    public static boolean validate(String name,String pass){
        boolean status=false;
        class_bo.Account account = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = ConnectionFactory.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "select * from account where first_name=? and last_name=?");
            ps.setString(1,account.getFirstName());
            ps.setString(2,account.getLastName());
            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }
}


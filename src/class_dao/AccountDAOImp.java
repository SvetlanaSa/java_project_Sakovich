package class_dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import class_bo.Account;
import class_con.ConnectionFactory;

/**
 * Created by РЎРІРµС‚Р° on 05.11.2016.
 */
public class AccountDAOImp implements IAccountDAO {

    public static ConnectionFactory connectionFactory1;
    List<class_bo.Account> Accounts;

    public static void setConnectionFactory1(ConnectionFactory connectionFactory1) {
        AccountDAOImp.connectionFactory1 = connectionFactory1;
    }

    @Override
    public class_bo.Account getAccount(int Account_ID) throws SQLException {
        PreparedStatement mystm  = null;
        ResultSet result = null;
        class_bo.Account account = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try {
            mystm = connection.prepareStatement("SELECT * FROM account_c WHERE account_id=?");
            mystm.setInt(1,Account_ID);
            result = mystm.executeQuery();

            while (result.next()) {
                account = new class_bo.Account(result.getString("first_name"),result.getString("last_name"),result.getString("country"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mystm.close();
            connection.close();
        }

        return account;
    }

    @Override
    public void insertAccount(class_bo.Account account) throws Exception {
   //public static int insertAccount(Account account)throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement mystm=null;
        try {
            mystm = connection.prepareStatement("INSERT INTO account_c (account_id, first_name,last_name,country) VALUES (account_seq.nextval,?,?,?)");
            mystm.setString(1, account.getFirstName());
            mystm.setString(2, account.getLastName());
            mystm.setString(3, account.getCountry());
            mystm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mystm.close();
            connection.close();
        }
   }



    @Override
    public void insertallAccount(List <class_bo.Account> accounts) throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement mystm= null;
        try {
            mystm = connection.prepareStatement("INSERT INTO account_c (first_name,last_name,country) VALUES (?,?,?)");
            for (class_bo.Account account: accounts) {
                mystm.setString(1, account.getFirstName());
                mystm.setString(2, account.getLastName());
                mystm.setString(3, account.getCountry());
                mystm.addBatch();
            }
            mystm.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mystm.close();
            connection.close();
        }
    }


    @Override
    public void updateAccount(class_bo.Account account) throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement mystm=null;
        try {
            mystm= connection .prepareStatement("UPDATE account_c SET " +
                    "first_name=?, last_name=?, country=?"+ "where account_id=?");
            mystm.setString(1, account.getFirstName());
            mystm.setString(2, account.getLastName());
            mystm.setString(3, account.getCountry());
            mystm.setInt(4, account.getAccount_ID());
            mystm.execute();
        } finally {
            if (mystm != null) {
                mystm.close();
                connection.close();
            }
        }
    }




    @Override
    public void deleteAccount(int Account_ID) throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        PreparedStatement mystm = null;
        class_bo.Account account = null;
        try {
            mystm = connection.prepareStatement("DELETE FROM account_c WHERE account_id=?");
            mystm.setInt(1,Account_ID);
            mystm.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mystm.close();
            connection.close();
        }
    }


    @Override
    public List<class_bo.Account> getAllAccount() throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        List<class_bo.Account> accounts = new ArrayList<class_bo.Account>();
        try {
            Statement mystm = connection.createStatement();
            ResultSet result  = mystm.executeQuery("SELECT * FROM account_c");
            while (result.next()){
                accounts.add(new class_bo.Account(result.getInt("account_id"),result.getString("first_name"),result.getString("last_name"),result.getString("country")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.close();
        }
        return accounts;
    }


    static int search(String FirstName, String LastName, int  Country) throws SQLException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        PreparedStatement statement = null;


        try {
            statement = con.prepareStatement("SELECT account_id FROM account_c WHERE first_name=? AND last_name=? AND country=?");
            statement.setString(1, FirstName);
            statement.setString(2, LastName);
            statement.setInt(3,  Country);
            ResultSet rs = statement.executeQuery();
            int accountId=0;
            while (rs.next()) {
                accountId = rs.getInt("Account_ID");
            }

            return accountId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }
}







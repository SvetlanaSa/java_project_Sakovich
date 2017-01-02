package pac.serlet;


import class_bo.Account;
import class_dao.AccountDAOImp;
import class_dao.IAccountDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Света on 21.11.2016.
 */
@WebServlet(name = "AccountController")
public class AccountController extends HttpServlet {

    private IAccountDAO dao;
    private static final long serialVersionUID = 1L;
    public static final String lIST_ACCOUNT= "/listAccount.jsp";
    public static final String INSERT_OR_EDIT = "/account.jsp";


    public AccountController() {
        dao = new AccountDAOImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter( "action" );

        if( action.equalsIgnoreCase( "delete" ) ) {
            forward = lIST_ACCOUNT;
           int Account_ID = Integer.parseInt( request.getParameter("Account_ID") );
               try {
                dao.deleteAccount(Account_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("accounts", dao.getAllAccount());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if( action.equalsIgnoreCase( "edit" ) ) {
            forward = INSERT_OR_EDIT;
            int Account_ID = Integer.parseInt( request.getParameter("Account_ID") );
            Account account = null;
            try {
                account = dao.getAccount(Account_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("account", account);
        }
        else if( action.equalsIgnoreCase( "insert" ) ) {
            forward = INSERT_OR_EDIT;
        }
        else {
            forward = lIST_ACCOUNT;
            try {
                request.setAttribute("accounts", dao.getAllAccount() );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher( forward );
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account= new Account();
        account.setFirstName( request.getParameter( "FirstName" ) );
        account.setLastName( request.getParameter( "LastName" ) );
        account.setCountry( request.getParameter( "Country" ) );
        String Account_ID= request.getParameter("Account_ID");

        if( Account_ID == null || Account_ID.isEmpty() )
            try {
                dao.insertAccount(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else {
            account.setAccount_ID( Integer.parseInt(Account_ID) );
            try {
                dao.updateAccount(account);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher( lIST_ACCOUNT );
        try {
            request.setAttribute("account", dao.getAllAccount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.forward(request, response);
    }
}
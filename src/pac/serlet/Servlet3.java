package pac.serlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import class_bo.Transaction;
import class_dao.ITransactionDAO;
import class_dao.TransactionDAO;

/**
 * Created by Света on 22.11.2016.
 */
@WebServlet(name = "Servlet3")
public class Servlet3 extends HttpServlet {
    private ITransactionDAO dao;
    private static final long serialVersionUID = 1L;
    public static final String lIST_TRANSACTION = "/listtransaction.jsp";
    public static final String INSERT_OR_EDIT = "/transaction.jsp";


    public Servlet3() {
        dao = new TransactionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action2 = request.getParameter("action2");

        if (action2.equalsIgnoreCase("delete")) {
            forward = lIST_TRANSACTION;
            int Transaction_ID = Integer.parseInt(request.getParameter("Transaction_ID"));
            try {
                dao.deleteTransaction(Transaction_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("Transaction", dao.getAllTrasaction());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action2.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int Transaction_ID = Integer.parseInt(request.getParameter("Transaction_ID"));
            class_bo.Transaction Transaction1 = null;
            try {
                Transaction1 = dao.getTransaction(Transaction_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("Transaction",Transaction1);
        } else if (action2.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
        } else {
            forward = lIST_TRANSACTION;
            try {
                request.setAttribute("Transaction", dao.getAllTrasaction());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        class_bo.Transaction Transaction1= new class_bo.Transaction();
        String Transaction_ID = request.getParameter("Transaction_ID");
        Transaction1.setFrom_Credit_Card(Long.parseLong(request.getParameter( "From_Credit_Card" ) ) );
        Transaction1.setTo_Credit_Card(Long.parseLong(request.getParameter( "To_Credit_Card" ) ) );
        Transaction1.setFrom_Credit_Card(Long.parseLong(request.getParameter( "From_Credit_Card" ) ) );
        Transaction1.setAmount(Double.parseDouble(request.getParameter( "Amount" ) ) );


        if (Transaction_ID == null || Transaction_ID.isEmpty())
            try {
                dao.insertTransaction(Transaction1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else {
            Transaction1.setTransaction_ID(Integer.parseInt(Transaction_ID ));
            try {
                dao.updateTransaction(Transaction1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(lIST_TRANSACTION);
        try {
            request.setAttribute("", dao.getAllTrasaction());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.forward(request, response);
    }
}


package pac.serlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import class_bo.Credit_Card;
import class_dao.ICreditCardDAO;
import class_dao.CreditClassDAO;


/**
 * Created by Света on 21.11.2016.
 */
@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {

    private ICreditCardDAO dao;
    private static final long serialVersionUID = 1L;
    public static final String lIST_CREDITCADR = "/listcreditcard.jsp";
    public static final String INSERT_OR_EDIT = "/creditcard.jsp";


    public Servlet2() {
        dao = new CreditClassDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action1 = request.getParameter("action1");

        if (action1.equalsIgnoreCase("delete")) {
            forward = lIST_CREDITCADR;
            int Credit_Card_ID = Integer.parseInt(request.getParameter("Credit_Card_ID"));
            try {
                dao.deleteCredit_Card(Credit_Card_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("Credit_Card", dao.getAllCredit());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action1.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int Credit_Card_ID = Integer.parseInt(request.getParameter("Credit_Card_ID"));
            class_bo.Credit_Card Credit_Card1 = null;
            try {
                Credit_Card1 = dao.getCredit_Card(Credit_Card_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("Credit_Card",Credit_Card1);
        } else if (action1.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
        } else {
            forward = lIST_CREDITCADR;
            try {
                request.setAttribute("Credit_Card", dao.getAllCredit());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        class_bo.Credit_Card Credit_Card1= new class_bo.Credit_Card();
        String Credit_Card_ID = request.getParameter("Credit_Card_ID");
        Credit_Card1.setAccount_ID(Integer.parseInt(request.getParameter( "Account_id" ) ) );
        Credit_Card1.setCash_amount(Double.parseDouble(request.getParameter( "Cash_amount" ) ) );

        if (Credit_Card_ID == null || Credit_Card_ID.isEmpty())
            try {
                dao.insertCredit_Card(Credit_Card1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else {
            Credit_Card1.setCredit_Card_ID(Integer.parseInt(Credit_Card_ID ));
            try {
                dao.updateCredit_Card(Credit_Card1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(lIST_CREDITCADR);
        try {
            request.setAttribute("", dao.getAllCredit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.forward(request, response);
    }
}

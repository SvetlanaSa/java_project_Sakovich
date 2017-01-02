package class_dao;

/**
 * Created by Света on 12.12.2016.
 */
public interface FactoryDAO {
    public AccountDAOImp getAccountDAOImp() ;
    public CreditClassDAO getCreditClassDAO();
    public TransactionDAO getTransactionDAO();

}
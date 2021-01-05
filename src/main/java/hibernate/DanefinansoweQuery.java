package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DanefinansoweQuery {
    Session session;
    Query query;


    public void addNewFinancialDataAnalysis(int year, double profitB, double economic_stocks, double assest,
                                            double totalSales, double operationProfit, double credit,
                                            double amort, double capital, double profit_net, int id_company)
            throws Exception {


        session = HibernateUtill.getSessionFactory().openSession();

        String query = "INSERT INTO `danefinansowe`(`id_dane`, `rok_bilansowy`, "
                + "`zysk_brutto`, `zapasy`, `aktywa_ogolem`, `przychodyS`, `zobowiazania`, `zysk_operacyjny`," +
                "`amortyzacja` , `kapital_wlasny` , `zysk_netto`, `id_firmy`)" +
                " SELECT NULL, '" + year + "', '" + profitB + "', '" + economic_stocks + "', '" + assest + "', '" +
                +totalSales + "', '" + operationProfit + "', '" + credit + "', '" + amort + "', '" + capital + "', '"
                + profit_net + "', '" + id_company + "'FROM DUAL WHERE NOT EXISTS (SELECT id_dane FROM danefinansowe " +
                "WHERE rok_bilansowy = '" +year+  "')";


        try {
            session.getTransaction().begin();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException error) {
            session.getTransaction().rollback();
            session.close();
        }
    }

    public Danefinansowe showID(int year, double profitB, double economic_stocks, double assest,
                                double totalSales, double credit, double operationProfit,
                                double amort, double capital, double profit_net, int id_company) {

        Danefinansowe d = null;
        session = HibernateUtill.getSessionFactory().openSession();
        String hql;
        hql = "from Danefinansowe as dane where dane.rokBilansowy ='" +year+ "' and dane.zyskBrutto= '" +profitB+ "' " +
                "and  dane.zapasy ='" +economic_stocks+ "' and dane.aktywaOgolem= '" +assest+ "' " +
                "and dane.przychodyS = '" +totalSales+ "' AND dane.zobowiazania = '" +credit+ "'" +
                " AND dane.zyskOperacyjny = '" +operationProfit+ "' AND dane.amortyzacja = '" +amort+ "'" +
                " AND dane.kapitalWlasny = '" + capital+ "' AND dane.zyskNetto = '" +profit_net+ "' " +
                "AND dane.idFirmy = '" +id_company+ "'";


        query = session.createQuery(hql);
        d = (Danefinansowe)query.uniqueResult();
        session.close();
        return d;

    }

    public  Danefinansowe checkYear(int idCompany){
        Danefinansowe df = null;
        session = HibernateUtill.getSessionFactory().openSession();
        String hql = "from Danefinansowe where idFirmy = '" + idCompany +" '";
        query = session.createQuery(hql);
        df = (Danefinansowe)query.setMaxResults(1).uniqueResult();
        session.close();
        return df;
    }

}

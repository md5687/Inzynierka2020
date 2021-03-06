package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class WskaznikiQuery {
    Session session;
    Query query;

    public void addNewPointers(double ROE, double ROA, double ROS, double operation_margin,
                               double ROI, double profit_margin, int idData)
            throws Exception {


        session = HibernateUtill.getSessionFactory().openSession();
        String query = "INSERT INTO `wskazniki` (`id_wskazniki`, `ROE`, `ROA`, `ROS`, `marza_operacyjna`, `ROI`, " +
                "`marza_ZB`, `id_dane`) VALUES (NULL,  '"  +ROE+ "', '" +ROA + "', '" + ROS + "', '" +operation_margin
                + "', '" +ROI+ "', '" +profit_margin+ "', '" +idData + "')";

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

    public Wskazniki showPointers(int idData){
        Wskazniki w = null;
        session = HibernateUtill.getSessionFactory().openSession();
        String hql = "from Wskazniki where idDane= '" + idData +" '";
        query = session.createQuery(hql);
        w = (Wskazniki) query.uniqueResult();
        session.close();
        return w;
    }

    public List<Wskazniki> WskaznikiSelectForYear(int dataID) {
        session = HibernateUtill.getSessionFactory().openSession();
        String hql = "from Wskazniki where idDane = '" + dataID + "'";
        Query query = session.createQuery(hql);
        List <Wskazniki> wq = query.list();
        session.close();
        int i = 0;
        for(Wskazniki w : wq){
            if(w.getIdDane() != dataID){
                wq.remove(i);
            }
            i++;
        }
        return wq;

    }

}


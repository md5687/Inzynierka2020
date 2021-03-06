package controllery.panelUsers.Company;

import com.jfoenix.controls.JFXButton;
import hibernate.Firma;
import hibernate.FirmaQuery;
import hibernate.Uzytkownik;
import hibernate.UzytkownikQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller_Company {

    @FXML
    private Pane changePaneC;

    @FXML
    private Pane paneChange;

    @FXML
    private Label nameCompany;

    @FXML
    private Label streetCompany;

    @FXML
    private Label cityCompany;

    @FXML
    private Label idCompany;

    @FXML
    private Label idUCom;


    @FXML
    private Pane behindPane;

    @FXML
    private JFXButton changeDataA;

    @FXML
    private Label idUshow;

    @FXML
    private Label idCAR;

    @FXML
    private JFXButton addCompanyBtn;

    Controller_CompanyFinancial financial;
    Controller_CompanyAnalysis analysis;
    Controller_CompanyForm comForm;
    Controller_Company_ChangeAdress adresCom;

    @FXML
    void goToDataFinancial(ActionEvent event) throws IOException {
        String link = "/fxml/panelUser_type/Company/CompanyDataFinancial.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller_Company.class.getResource(link));
        Pane newPane = loader.load();
        paneChange.getChildren().add(newPane);

        Controller_CompanyFinancial cf = loader.getController();
        financial = cf;
        int idD = Integer.parseInt(idUshow.getText());
        cf.comboBox(idD);
        cf.readIdCompany(idD);


    }

    @FXML
    void goToForm(ActionEvent event) throws IOException {
        String linkF = "/fxml/panelUser_type/Company/CompanyForm.fxml";
        FXMLLoader load = new FXMLLoader();
        load.setLocation(Controller_Company.class.getResource(linkF));
        Pane loadP = load.load();
        paneChange.getChildren().add(loadP);

        Controller_CompanyForm fCom = load.getController();
        comForm = fCom;
         int idU = Integer.parseInt(idUCom.getText());
        fCom.readIdUser(idU);
        fCom.readPane(this.behindPane);



    }

    @FXML
    void goToChangeData(ActionEvent event) throws IOException {
        String link = "/fxml/panelUser_type/Company/CompanyChangeAdress.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller_Company.class.getResource(link));
        Pane newPane = loader.load();
        paneChange.getChildren().add(newPane);

        Controller_Company_ChangeAdress changeA = loader.getController();
        adresCom = changeA;
        int id = Integer.parseInt(idCompany.getText());
        FirmaQuery fq = new FirmaQuery();
        Firma f = fq.showCompanyData(id);
        changeA.readDataCompany(f.getNazwaFirmy(), f.getUlica(), f.getNumerBudynku(), f.getNumerLokalu(),
                f.getKodPocztowy(), f.getMiasto());
        changeA.readIdCompany(id);
    }

    @FXML
    void goToDataAnalysis(ActionEvent event) throws IOException {
        String link = "/fxml/panelUser_type/Company/CompanyAnalysis.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller_Company.class.getResource(link));
        Pane newPane = loader.load();
        paneChange.getChildren().add(newPane);

        Controller_CompanyAnalysis analsis = loader.getController();
        analysis = analsis;
        int idCpman = Integer.parseInt(idCAR.getText());
        analsis.comboBox(idCpman);
       analsis.readIdCompany(idCpman);


    }

    public void readCompanyData(String nameC, String street, String numberBuilding, String local, String postC,
                                String city, int idCompany) {

        FirmaQuery comData = new FirmaQuery();
            Firma compa = comData.showCompanyData(idCompany);

        if (compa != null) {
            addCompanyBtn.setVisible(false);
            nameCompany.setText(nameC);
            if (!local.isEmpty()) {
                streetCompany.setText("ul. " + street + " " + numberBuilding + "/" + local);
            } else {
                streetCompany.setText("ul. " + street + " " + numberBuilding);
            }
            cityCompany.setText(postC + " " + city);

        }
    }


// read id company for analysis data show

    public void readIdCompany(int id){
        idCompany.setText(Integer.toString(id));
        idCompany.setVisible(false);
    }

    public void readIdUser (int idU){
        idUCom.setText(Integer.toString(idU));
        idUCom.setVisible(false);
    }

     // read id company for data financial
    public void readIDu (int idUS){
        idUshow.setText(Integer.toString(idUS));
        idUshow.setVisible(false);
    }

    // read id company for analysis - combobox

    public void readIdCAR (int idC ){
        idCAR.setText(Integer.toString(idC));
        idCAR.setVisible(false);
    }

    public void getChangePaneC (Pane p){
        behindPane = p;
    }


}

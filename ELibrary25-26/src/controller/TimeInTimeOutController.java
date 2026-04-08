package controller;


import javax.swing.*;

import model.DAOs.FacilityLogin.FacilityLoginDAOImp;
import view.front_pages.*;

import javax.swing.Timer;

public class TimeInTimeOutController {

    FacilityLoginDAOImp daoFL = new FacilityLoginDAOImp();

    public TimeInTimeOutController(FacilityLogin fl) {

        String facilityCode = fl.getSelectedFacility();
        String cardNo = fl.getCardNo();
        String patronID = fl.getPatronID();

        String[] patronData = daoFL.timeInTimeOutPatron(patronID, facilityCode, cardNo);
        
        if (patronData == null) {
            fl.showPatronNotRegistered();
            return;
        }

        String TOValue = patronData[5] != null ? patronData[5] : "--";

        fl.patronIDValue.setText(patronData[0]);
        fl.fullNameValue.setText(patronData[1]);
        fl.college_campusValue.setText(patronData[2]);
        fl.patronTypeValue.setText(patronData[3]);
        fl.timeInValue.setText(patronData[4]);
        fl.timeOutValue.setText(TOValue);

        
        Timer timer = new Timer(5000, e -> {
            fl.patronIDValue.setText("--");
            fl.fullNameValue.setText("--");
            fl.college_campusValue.setText("--");
            fl.patronTypeValue.setText("--");
            fl.timeInValue.setText("--");
            fl.timeOutValue.setText("--");
        });

        timer.setRepeats(false); 
        timer.start();
    }
}

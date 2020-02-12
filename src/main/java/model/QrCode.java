package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QrCode {

    private String uid;
    private String code;
    private String description;
    private String startDate;
    private String validityDate;

    public QrCode(String uid, String code, String description, String startDate, String validityDate) throws ParseException {
        this.uid = uid;
        this.code = code;
        this.description = description;
        this.startDate =startDate;  ;
        this.validityDate = validityDate;
    }

    public QrCode() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description = description; }

    public String getStartDate() {return startDate;}

    public void setStartDate(String startDate) {this.startDate = startDate;}

    public String getValidityDate() { return validityDate; }

    public void setValidityDate(String validityDate) { this.validityDate = validityDate; }
}

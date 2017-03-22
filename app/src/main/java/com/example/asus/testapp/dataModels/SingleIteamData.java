package com.example.asus.testapp.dataModels;

import java.util.Date;
import io.realm.RealmObject;

/**
 * Created by ASUS on 3/22/2017.
 */

public class SingleIteamData extends RealmObject {

    private String inputDate;
    private String suppliers;
    private String income;
    private String spending;
    private String subjects;
    private String course;
    private int suppliersId;

    public SingleIteamData() {
    }

    public SingleIteamData(String inputDate, String suppliers, String income, String spending, String subjects, String course, int suppliersId) {
        this.inputDate = inputDate;
        this.suppliers = suppliers;
        this.income = income;
        this.spending = spending;
        this.subjects = subjects;
        this.course = course;
        this.suppliersId = suppliersId;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getSpending() {
        return spending;
    }

    public void setSpending(String spending) {
        this.spending = spending;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
    }

}

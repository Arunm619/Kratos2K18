package com.kratos18.kratos2k18;

/**
 * Created by root on 18/1/18.
 */

class Student {
    private int Form_id;
    private String Form_date;
    private String Status;
    private String Textname, Collegename, Email42, Dept;
    private Long Textphone;
    private String Qrcode;

    public String getQrcode() {
        return Qrcode;
    }

    public void setQrcode(String qrcode) {
        Qrcode = qrcode;
    }

    private String Mc4wp_checkbox;


    private String UUID;


    Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "Form_id=" + Form_id +
                ", Form_date='" + Form_date + '\'' +
                ", Status='" + Status + '\'' +
                ", Textname='" + Textname + '\'' +
                ", Collegename='" + Collegename + '\'' +
                ", Email42='" + Email42 + '\'' +
                ", Dept='" + Dept + '\'' +
                ", Textphone=" + Textphone +
                ", Qrcode='" + Qrcode + '\'' +
                ", Mc4wp_checkbox='" + Mc4wp_checkbox + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }

    public int getForm_id() {
        return Form_id;
    }

    public void setForm_id(int form_id) {
        Form_id = form_id;
    }

    public String getForm_date() {
        return Form_date;
    }

    public void setForm_date(String form_date) {
        Form_date = form_date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTextname() {
        return Textname;
    }

    public void setTextname(String textname) {
        Textname = textname;
    }

    public String getCollegename() {
        return Collegename;
    }

    public void setCollegename(String collegename) {
        Collegename = collegename;
    }

    public String getEmail42() {
        return Email42;
    }

    public void setEmail42(String email42) {
        Email42 = email42;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public Long getTextphone() {
        return Textphone;
    }

    public void setTextphone(Long textphone) {
        Textphone = textphone;
    }

    public String getMc4wp_checkbox() {
        return Mc4wp_checkbox;
    }

    public void setMc4wp_checkbox(String mc4wp_checkbox) {
        Mc4wp_checkbox = mc4wp_checkbox;
    }


    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }


    public Student(int form_id, String form_date, String status, String textname, String collegename, String email42, String dept, Long textphone, String mc4wp_checkbox) {
        Form_id = form_id;
        Form_date = form_date;
        Status = status;
        Textname = textname;
        Collegename = collegename;
        Email42 = email42;
        Dept = dept;
        Textphone = textphone;
        Mc4wp_checkbox = mc4wp_checkbox;
    }
}

package com.kratos18.kratos2k18;

/**
 * Created by root on 18/1/18.
 */

class Student {

    private boolean ate;
    private String Textname, Collegename, Email, Dept;
    private Long Textphone;
    private String Qrcode;
    private String Gender;

    private String UUID;
    String participatedevents;

    public String getParticipatedevents() {
        return participatedevents;
    }

    public void setParticipatedevents(String participatedevents) {
        this.participatedevents = participatedevents;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "ate=" + ate +
                ", Textname='" + Textname + '\'' +
                ", Collegename='" + Collegename + '\'' +
                ", Email='" + Email + '\'' +
                ", Dept='" + Dept + '\'' +
                ", Textphone=" + Textphone +
                ", Qrcode='" + Qrcode + '\'' +
                ", Gender='" + Gender + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }

    public boolean isAte() {
        return ate;
    }

    public void setAte(boolean ate) {
        this.ate = ate;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getQrcode() {
        return Qrcode;
    }

    public void setQrcode(String qrcode) {
        Qrcode = qrcode;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Student(boolean ate, String textname, String collegename, String email, String dept, Long textphone, String qrcode, String gender, String UUID) {
        this.ate = ate;
        Textname = textname;
        Collegename = collegename;
        Email = email;
        Dept = dept;
        Textphone = textphone;
        Qrcode = qrcode;
        Gender = gender;
        this.UUID = UUID;
    }
}

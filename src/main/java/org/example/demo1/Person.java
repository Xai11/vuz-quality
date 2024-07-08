package org.example.demo1;

public class Person {
    private String name;
    private String subject;
    private String prof;
    private int exp;
    private String degr;
    private String rank;
    private int hours;
    private String progr;

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getProf() {
        return prof;
    }

    public int getExp() {
        return exp;
    }

    public String getDegr() {
        return degr;
    }

    public String getRank() {
        return rank;
    }

    public int getHours() {
        return hours;
    }

    public String getProgr() {
        return progr;
    }

    public Person(String name, String subject, String prof, int exp, String degr, String rank, int hours, String progr) {
        this.name = name;
        this.subject = subject;
        this.prof = prof;
        this.exp = exp;
        this.degr = degr;
        this.rank = rank;
        this.hours = hours;
        this.progr = progr;
    }
}

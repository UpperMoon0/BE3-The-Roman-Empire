package model;

/**
 *
 * @author Nhat
 */

public class Consultant {
    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private String address;
    private int termNum;
    private int salary;
    private boolean noble;
    private int kingdomId;

    public Consultant () {}

    public Consultant(int id, String username, String password, String name, int age, String address, int termNum, int salary, boolean noble, int kingdomId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.termNum = termNum;
        this.salary = salary;
        this.noble = noble;
        this.kingdomId = kingdomId;
    }

    public Consultant(String username, String password, String name, int age, String address, int termNum, int salary, boolean noble, int kingdomId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.termNum = termNum;
        this.salary = salary;
        this.noble = noble;
        this.kingdomId = kingdomId;
    }
    
    public int getId () {
        return id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public int getTermNum () {
        return termNum;
    }

    public void setTermNum (int termNum) {
        this.termNum = termNum;
    }

    public int getSalary () {
        return salary;
    }

    public void setSalary (int salary) {
        this.salary = salary;
    }

    public boolean isNoble () {
        return noble;
    }

    public void setNoble (boolean noble) {
        this.noble = noble;
    }

    public int getKingdomId () {
        return kingdomId;
    }

    public void setKingdomId (int kingdomId) {
        this.kingdomId = kingdomId;
    }
}

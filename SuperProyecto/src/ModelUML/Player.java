/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.math.BigDecimal;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class Player {

    private String fullName;
    private String nickName;
    private BigDecimal salary;
    private String email;
    private Team team;

    public Player() {
    }

    public Player(String fullName, String nickName, BigDecimal salary, String email) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.salary = salary;
        this.email = email;
    }

    public Player(String fullName, String nickName, BigDecimal salary, String email, Team team) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.salary = salary;
        this.email = email;
        this.team = team;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}

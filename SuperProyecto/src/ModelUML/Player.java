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

    /**
     * Creacion de los atributos fullName, nickName, salary, email, team.
     */
    private String fullName;
    private String nickName;
    private BigDecimal salary;
    private String email;
    private Team team;

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public Player() {
    }

    /**
     * Constructor lleno
     *
     * @param fullName (requerido) Nombre del jugador
     * @param nickName (requerido) Alias del jugador
     * @param salary (requerido) Salario del jugador
     * @param email (requerido) Email del jugador
     */
    public Player(String fullName, String nickName, BigDecimal salary, String email) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.salary = salary;
        this.email = email;
    }

    /**
     * Constructor lleno
     *
     * @param fullName (requerido) Nombre del jugador
     * @param nickName (requerido) Alias del jugador
     * @param salary (requerido) Salario del jugador
     * @param email (requerido) Email del jugador
     * @param team (requerido) Equipo del jugador
     */
    public Player(String fullName, String nickName, BigDecimal salary, String email, Team team) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.salary = salary;
        this.email = email;
        this.team = team;
    }

    /**
     * Es un Get que devuelve el nombre del jugador
     *
     * @return fullName el nombre completo que se devuelve
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Es un Set que establece el nombre del jugador
     *
     * @param fullName el nombre completo establecido
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Es un Get que devuelve el alias del jugador
     *
     * @return nickName el alias que se devuelve
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Es un Set que establece el alias del jugador
     *
     * @param nickName el alias del jugador establecido
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Es un Get que devuelve el salario del jugador
     *
     * @return salary el salario del jugador que se devuelve
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Es un Set que establece del salario del jugador
     *
     * @param salary el salario del jugador establecido
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Es un Get que devuelve el email del jugador
     *
     * @return email el email del jugador que se devuelve
     */
    public String getEmail() {
        return email;
    }

    /**
     * Es un Set que establece el email del jugador
     *
     * @param email el email del jugador establecido
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Es un Get que devuelve el nombre del equipo en el que esta
     *
     * @return team devuelve el nombre del equipo en el que esta
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Es un Set que establece el nombre del equipo en el que esta
     *
     * @param team el nombre del equipo establecido
     */
    public void setTeam(Team team) {
        this.team = team;
    }

}

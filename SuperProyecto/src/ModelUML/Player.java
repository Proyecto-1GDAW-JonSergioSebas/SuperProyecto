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
     * Constructor lleno.
     *
     * @param fullName (requerido) El nombre del jugador
     * @param nickName (requerido) El alias del jugador
     * @param salary (requerido) El salario del jugador
     * @param email (requerido) El email del jugador
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
     * @param fullName (requerido) El nombre del jugador
     * @param nickName (requerido) El alias del jugador
     * @param salary (requerido) El salario del jugador
     * @param email (requerido) El email del jugador
     * @param team (requerido) El nombre del equipo en el que esta el jugador
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
     * @return fullName devuelve el nombre
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Es un Set que establece el nombre del jugador
     *
     * @param fullName nombre del jugador establecido
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Es un Get que devuelve el alias del jugador
     *
     * @return nickName devuelve el alias
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Es un Set que establece el alias del jugador
     *
     * @param nickName establece el alias del jugador
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Es un Get que devuelve el salario del jugador
     *
     * @return salary devuelve el salario
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Es un Set que establece el salario del jugador
     *
     * @param salary establece el salario
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Es un Get que devuelve el Email del jugador
     *
     * @return email devuelve el email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Es un Set que establece el Email del jugador
     *
     * @param email establece el email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Es un Get que devuelve el equipo en el que esta el jugador
     *
     * @return team devuelve el nombre del equipo
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Es un Set que establece el equipo en el que esta el jugador
     *
     * @param team establece el nombre del equipo
     */
    public void setTeam(Team team) {
        this.team = team;
    }

}

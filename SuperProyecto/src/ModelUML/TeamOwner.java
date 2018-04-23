/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class TeamOwner {

    private String fullName;
    private String telephone;

    /**
     * Sole constructor. (For invocation by subclass constructors, typically
     * implicit.)
     */
    public TeamOwner() {
    }

    /**
     * 
     * @param fullName (requiered) the name of the owner
     * @param telephone  (requiered) the telephone of the owner
     *      values 9 numbers
     */
    public TeamOwner(String fullName, String telephone) {
        this.fullName = fullName;
        this.telephone = telephone;
    }

    /**
     * This is a setter which gets the fullName
     *
     * @param fullName - the name to be get
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This is a setter which sets the fullName
     *
     * @param fullName - the name to be set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * This is a setter which gets the telephone
     *
     * @param telephone - the telephone to be get
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This is a setter which sets the telephone
     *
     * @param telephone - the telephone to be set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}

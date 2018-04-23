/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.ArrayList;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class League {

    private String leagueName;
    private ArrayList<MatchSet> matchsets;

    public League(String leagueName, ArrayList<MatchSet> matchsets) {
        this.leagueName = leagueName;
        this.matchsets = matchsets;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public ArrayList<MatchSet> getMatchsets() {
        return matchsets;
    }

    public void setMatchsets(ArrayList<MatchSet> matchsets) {
        this.matchsets = matchsets;
    }

}

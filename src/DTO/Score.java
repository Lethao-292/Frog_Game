/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author OS
 */
public class Score implements Comparable<Score>{

    String medal;
    String name;
    int score;

    public Score(String medal, String name, int score) {
        this.medal = medal;
        this.name = name;
        this.score = score;
    }

    public String getMedal() {
        return medal;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return medal + ";" + name + ";" + score + ";";
    }

    @Override
    public int compareTo(Score obj) {
        return obj.getScore() - this.getScore();
    }
}

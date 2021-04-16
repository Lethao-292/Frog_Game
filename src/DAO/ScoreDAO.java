/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Score;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author OS
 */
public class ScoreDAO {

    private static final String FILENAME = "Score.txt";

    public static List<Score> openFile() {
        List<Score> list = new ArrayList<>();
        FileReader f = null;
        BufferedReader bf = null;
        try {
            f = new FileReader(FILENAME);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                String tmp = bf.readLine();
                String[] detail = tmp.split(";");
                if (detail.length == 3) {
                    list.add(new Score(detail[0].trim(), detail[1].trim(), Integer.parseInt(detail[2].trim())));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eror at Open File " + e);
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static boolean writeFile(List<Score> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        PrintWriter p = null;
        try {
            p = new PrintWriter(FILENAME);
            for (Score score : list) {
                p.write(score.toString());
                p.write("\n");
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eror at WriteFile " + e);
            return false;
        } finally {
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
            }
        }
    }

}

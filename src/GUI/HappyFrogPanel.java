/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ScoreDAO;
import DTO.Frog;
import DTO.PipeLine;
import DTO.Score;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author OS
 */
public class HappyFrogPanel extends javax.swing.JPanel {

    private final String BGDAYFILE = "background.png";
    private final String BGNIGHTFILE = "nightbackground.png";
    private final String FROGFILE = "frog.png";
    private final String PIPEUPFILE = "pipeup.png";
    private final String PIPEDOWNFILE = "pipedown.png";
    private final int GAMEWIDTH = 2200;
    private final int GAMEHEIGHT = 800;
    private final int FROGSIZE = 80;
    private final int PIPENUM = 6;
    private final int XFROG = 500;

    /**
     * Creates new form HappyFrogPanel
     */
    Frog frog;
    ArrayList<PipeLine> listPipeUp;
    ArrayList<PipeLine> listPipeDown;
    int score;
    boolean gameOver;
    boolean isPlay;
    Timer t;
    int level;
    String background;

    int scoreCheck;
    boolean check;

    public HappyFrogPanel() {
        initComponents();
        initGame();
        t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int h;
                for (PipeLine pipeLine : listPipeUp) {
                    //control pipeline
                    pipeLine.setX(pipeLine.x - level);
                    
                    if (pipeLine.x <= -FROGSIZE){
                        h = randomHeight();
                        pipeLine.setX(GAMEWIDTH);
                        pipeLine.setHeight(h);
                    }
                    
                    if(pipeLine.intersects(frog)){
                        gameOver = true;
                        isPlay = false;
                    }
                    
                    if (pipeLine.getCheckPipe() && pipeLine.contains(XFROG, pipeLine.y)) {
                        score++;
                        pipeLine.setCheckPipe(false);
                    }
                }

                for (PipeLine pipeLine : listPipeDown) {
                    //control pipeline
                    pipeLine.setX(pipeLine.x - level);
                    if (pipeLine.x <= -FROGSIZE) {
                        h = randomHeight();
                        pipeLine.setX(GAMEWIDTH);
                        pipeLine.setY(GAMEHEIGHT - h);
                        pipeLine.setHeight(h);
                    }

                    //control game
                    if (pipeLine.intersects(frog)) {
                        gameOver = true;
                        isPlay = false;
                    }
                }

                //control frog
                frog.setY(frog.y + level);
                if (frog.y <= 0 || frog.y >= GAMEHEIGHT - FROGSIZE) {
                    gameOver = true;
                }

                //conrol level
                if (!check && score != 0 && score != scoreCheck) {
                    check = true;
                }
                if (check && score % 10 == 0) {
                    scoreCheck = score;
                    level++;
                    check = false;
                    if (score % 20 == 0) {
                        changeBG();
                    }
                }

                //game Over
                if (gameOver) {
                    saveScore();
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to start a new game", "New game", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        initGame();
                        t.start();
                    } else {
                        System.exit(0);
                    }
                }
                repaint();
            }
        });
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        //Draw Background
        ImageIcon icon = new ImageIcon(background);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, GAMEWIDTH, GAMEHEIGHT, null);

        //Draw Frog
        frog.draw(g);

        //Draw Pipe
        for (PipeLine pipeLine : listPipeUp) {
            pipeLine.draw(g);
        }
        for (PipeLine pipeLine : listPipeDown) {
            pipeLine.draw(g);
        }

        //Draw Score
        lbScore.setText("Score: " + score);

        //Draw Level
        lbLevel.setText("Level " + (level / 2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPause = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbScore = new javax.swing.JLabel();
        lbLevel = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        btnPause.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lbScore.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        lbScore.setText("Score: 0");

        lbLevel.setFont(new java.awt.Font("Tahoma", 2, 48)); // NOI18N
        lbLevel.setText("Level");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbScore, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(btnPause)
                        .addGap(59, 59, 59)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbScore))
                .addGap(26, 26, 26)
                .addComponent(lbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if (isPlay) {
            frog.setY(frog.y - (level + 5) * 10);
        } else {
            if (!gameOver) {
                isPlay = true;
                t.start();
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if (isPlay) {
            isPlay = false;
            t.stop();
        } else {
            if (!gameOver) {
                isPlay = true;
                t.start();
            }
        }
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        isPlay = false;
        t.stop();
        if (!gameOver) {
            int choice = JOptionPane.showConfirmDialog(null, "Your game is not finish. Exit?", "Option", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                saveScore();
                System.exit(0);
            }
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        isPlay = false;
        t.stop();
        if (!gameOver) {
            int choice = JOptionPane.showConfirmDialog(null, "Your game is not finish. Save?", "Option", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                saveScore();
                choice = JOptionPane.showConfirmDialog(null, "Do you want to start a new game?", "New game", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    initGame();
                    t.start();
                } else {
                    System.exit(0);
                }
            }

        } else {
            saveScore();
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to start a new game", "New game", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                initGame();
                t.start();
            } else {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (isPlay) {
                frog.setY(frog.y - (level + 5) * 10);
            } else {
                if (!gameOver) {
                    isPlay = true;
                    t.start();
                }
            }
        }
    }//GEN-LAST:event_formKeyReleased

    private void initGame() {
        score = 0;
        isPlay = true;
        gameOver = false;
        level = 4;
        scoreCheck = 0;
        background = BGDAYFILE;
        check = false;

        //init frog
        frog = new Frog(FROGFILE, XFROG, 50, FROGSIZE, FROGSIZE);

        //init pipe
        listPipeUp = new ArrayList<>();
        listPipeDown = new ArrayList<>();
        int h;
        int x = 2200;
        for (int i = 0; i < PIPENUM; i++) {
            h = randomHeight();
            listPipeUp.add(new PipeLine(PIPEUPFILE, x, 0, FROGSIZE, h));
            h = randomHeight();
            listPipeDown.add(new PipeLine(PIPEDOWNFILE, x, GAMEHEIGHT - h, FROGSIZE, h));
            x += 400;
        }
    }

    private int randomHeight() {
        Random r = new Random();
        int h = r.nextInt(150) + 150;
        return h;
    }

    public void saveScore() {
        String medal;
        ImageIcon icon;
        String name;
        List<Score> list = ScoreDAO.openFile();

        if (score < 10) {
            medal = "No Medal";
        } else if (score < 20) {
            medal = "Bronze Medal";
        } else if (score < 30) {
            medal = "Silver Medal";
        } else if (score < 40) {
            medal = "Gold Medal";
        } else {
            medal = "Platinum Medal";
        }
        icon = new ImageIcon(medal + ".png");

        if (highestScore(list)) {
            int choice = JOptionPane.showConfirmDialog(this, "You got HIGHEST SCORE. Do you want to save your result?", "Result", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
            if (choice == JOptionPane.YES_OPTION) {
                name = JOptionPane.showInputDialog("Your name: ");
                list.add(new Score(medal, name, score));
                Collections.sort(list);
                if (ScoreDAO.writeFile(list)) {

                }
            }
        }
        int choice = JOptionPane.showConfirmDialog(this, "You got " + medal + ". Do you want to save your result?", "Result", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (choice == JOptionPane.YES_OPTION) {
            name = JOptionPane.showInputDialog("Your name: ");
            list.add(new Score(medal, name, score));
            Collections.sort(list);
            if (ScoreDAO.writeFile(list)) {

            }
        }
    }

    private void changeBG() {
        if (background.equals(BGDAYFILE)) {
            background = BGNIGHTFILE;
        } else {
            background = BGDAYFILE;
        }
    }

    private boolean highestScore(List<Score> list) {
        for (Score score1 : list) {
            if (score1.getScore() >= score) {
                return false;
            }
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JLabel lbScore;
    // End of variables declaration//GEN-END:variables
}

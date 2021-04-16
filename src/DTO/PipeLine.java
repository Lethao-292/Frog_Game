/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author OS
 */
public class PipeLine extends Rectangle {
    String fileName;
    boolean checkPipe = true;

    public PipeLine(String fileName, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.fileName = fileName;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setCheckPipe(boolean checkPipe){
        this.checkPipe = checkPipe;
    }
    
    public boolean getCheckPipe(){
        return checkPipe;
    }

    public void draw (Graphics g){
        ImageIcon icon = new ImageIcon(fileName);
        Image img = icon.getImage();
        g.drawImage(img, this.x, this.y, this.width, this.height, null);
    }
    
    
}

package Chatting;

import javax.swing.*;
import java.awt.*;
public class NoTitleBarFrameTest {
 public static void main(String[] args) {
  JFrame f = new JFrame();
  f.setUndecorated(true);
  f.setSize(300,300);
  f.setVisible(true);
  //JWindow w = new JWindow();
  //w.setSize(300,300);
  //w.setVisible(true);
 }
}
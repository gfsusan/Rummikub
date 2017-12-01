package rummikub;

import javax.swing.ImageIcon;
import java.awt.Image;

//creates one tile
public class Tile {
   
   /** tileID
    * 0-25 : red
    * 26-51 : orange
    * 52-77 : blue
    * 78-103 : black
    * 104-105 : joker
    * -1 : blank
    */

   private int tileID;      // tile priority
   private int tileNum;   // number written in tile
   private boolean isUsed;   // true == on the board, false == in the deck
   
   private Image tileImage;
   //private ImageIcon tileImageIcon;
   
   // TODO pathSep
   //private String pathSep = "";
   
   // tile constructor for blank tile
   public Tile() {
      tileNum = -1;
      tileID = -1;
      isUsed = true;
      
      String pathSep = System.getProperty("file.separator");
      if(pathSep.equals("\\")) {
         pathSep = "\\\\";
      }
      
      String imagePath = "pics" + pathSep + this.getColor() + ".png";
      tileImage = (new ImageIcon(imagePath)).getImage();
   }
   
   // tile constructor for color tile
   public Tile(int id, int num) {
      tileNum = num;
      tileID = id;
      isUsed = false;
      
      String pathSep = System.getProperty("file.separator");
      if(pathSep.equals("\\")) {
         pathSep = "\\\\";
      }
      
      String imagePath = "pics" + pathSep + this.getColor() + tileNum + ".png";
      tileImage = (new ImageIcon(imagePath)).getImage();
   }
   
   // return tile's color
   private String getColor() {
	   
      if (0 <= tileID && tileID <= 25) {
         return "red";
      }
      else if (26 <= tileID && tileID <= 51) {
         return "orange";
      }
      else if (52 <= tileID && tileID <= 77) {
         return "blue";
      }
      else if (78 <= tileID && tileID <= 103) {
         return "black";
      }
      else if (tileID == 104 || tileID == 105) {
         return "joker";
      }
      else if (tileID == -1) {
         return "blank";
      }
      else {
         System.out.println("There is correct tile color");
         return "";
      }
   }
   
   // return tileID
   public int getTileID() {
      return tileID;
   }
   
   // return isUsed;
   public boolean getIsUsed() {
      return isUsed;
   }
   
   // set isUsed to true(on the board)
   public void setIsUsed() {
      isUsed = true;
   }
   
   // return Image
   public Image getImage() {
      return tileImage;
   }
   
   public int getTileIndex() {
	      return (tileID / 2) + 1;
	   }
   
}
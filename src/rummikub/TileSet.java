package rummikub;

import java.util.ArrayList;

public class TileSet {
   
   private ArrayList <Integer> set = new ArrayList<Integer>();
   
   public TileSet() {
      
   }
   
   public TileSet(ArrayList <Integer> rset) {
      this.set = rset;
   }
   
   
   // TODO if needed use, else delete
   
   public int getSize() {
      return set.size();
   }
   /*
   public ArrayList<Integer> getTileSet() {
      return this.set;
   }
   
   public int getTile(int num) {
      return set.get(num);
   }
   */
}
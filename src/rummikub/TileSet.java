/**
 * 해야할 일!!!!!!!!!!!!!!!!!!!!
 * BoardChecker에서 tileSet인식해서 tileSet[]저장하는 함수
 * tileSet[]의 모든 원소(tileSet)에 대해서 valid한 지 check 후 , 하나라도 invalid하면 false!!!!!
 */

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
/**
 * Created by perunjac000 on 2/16/2017.
 */
public class Stats {

   private static boolean levelOne = true;
   private static boolean isPause = false;
   private static boolean isMenu = false;
   private static boolean isEnd = false;
   private static boolean isWin = false;
   private static boolean levelTwo = false;
   private static boolean levelThree = false;

    public static int foodscore = 0;

    public static void updateFoodscore() {foodscore++;}

    public static boolean levelTwo() { return levelTwo; }

    public static boolean levelThree(){return levelThree;}


   public static boolean levelOne() { return levelOne; }

   public static boolean isPause(){
      return isPause;
   }

   public static boolean isMenu(){
      return isMenu;
   }

   public static boolean isEnd(){
      return isEnd;
   }

   public static boolean isWin(){
      return isWin;
   }

   public static void setLevelTwo(){ levelTwo = true; }
   public static void setLevelThree(){ levelThree = true; }

   public static void stopPlay(){
      levelOne = false;
   }
   public static void startPause(){
      isPause = true;
   }
   public static void stopPause(){
      isPause = false;
   }
   public static void startMenu(){
      isMenu = true;
   }
   public static void stopMenu(){
      isMenu = false;
   }

   public static void startLevelOne(){
       isMenu = false;
       levelOne = true;
   }

   public static void startLevelTwo(){
      levelOne = false;
      levelTwo = true;
   }

   public static void startLevelThree(){
      levelTwo = false;
      levelThree = true;
   }

   public static void endGame(){
      isEnd = true;
      levelOne = false;
   }

   public static void Win(){
      isWin = true;
      levelOne = false;
   }

   public static void startGame(){
      isEnd = false;
      levelOne = true;
   }

   public static void togglePause(){
      if(isPause){
         isPause = false;
      }
      else
         isPause = true;
   }

   public static void togglePlay(){
      if(levelOne){
         levelOne = false;
      }
      else
         levelOne = true;
   }

   public static void toggleMenu(){
      if(isMenu)
         isMenu = false;
      else
         isMenu = true;
   }



}



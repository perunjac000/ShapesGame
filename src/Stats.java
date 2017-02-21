/**
 * Created by perunjac000 on 2/16/2017.
 */
public class Stats {

   private static boolean isPlay = false;
   private static boolean isPause = false;
   private static boolean isMenu = true;
   private static boolean isEnd = false;
   private static boolean levelTwo = false;
   private static boolean levelThree = false;

    public static int foodscore = 0;

    public static void updateFoodscore() {foodscore++;}

    public static boolean levelTwo() { return levelTwo; }


   public static boolean isPlay() { return isPlay; }

   public static boolean isPause(){
      return isPause;
   }

   public static boolean isMenu(){
      return isMenu;
   }

   public static boolean isEnd(){
      return isEnd;
   }
   public static void startPlay(){
      isPlay = true;
   }

   public static void setLevelTwo() { levelTwo = false; }

   public static void stopPlay(){
      isPlay = false;
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

   public static void startLevelTwo(){

   }

   public static void endGame(){
      isEnd = true;
      isPlay = false;
   }

   public static void startGame(){
      isEnd = false;
      isPlay = true;
   }

   public static void togglePause(){
      if(isPause){
         isPause = false;
      }
      else
         isPause = true;
   }

   public static void togglePlay(){
      if(isPlay){
         isPlay = false;
      }
      else
         isPlay = true;
   }

   public static void toggleMenu(){
      if(isMenu)
         isMenu = false;
      else
         isMenu = true;
   }



}



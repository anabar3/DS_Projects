package e4;

import java.util.ArrayList;
import java.util.Arrays;

public class BilliardTable {
    private boolean underway;
    private ArrayList<BilliardBall> tableBalls;
    private ArrayList<BilliardBall> pocketBalls;

    public BilliardTable() { //all balls out of the table
        underway = false;
        tableBalls = new ArrayList<>();
        pocketBalls = new ArrayList<>(Arrays.asList(BilliardBall.values()));
    }

    public void startGame(){ //all balls on the table
        underway = true;
        tableBalls = new ArrayList<>(Arrays.asList(BilliardBall.values()));
        pocketBalls = new ArrayList<>();
    }
    public void pocketBall(BilliardBall ball){
        if(!underway) throw new IllegalStateException("Game is not underway");
        if (!tableBalls.contains(ball)) throw new IllegalArgumentException("The ball is not on the table");

        if (ball == BilliardBall.CUEBALL && underway){ //If CUEBALL but the game is still going
            return;
        } else if (ball == BilliardBall.BALL8){ // If it is BALL8
            underway=false;
        }
        pocketBalls.add(ball);
        tableBalls.remove(ball);
    }

    public BilliardBall[] ballsOnTable(){
        BilliardBall[] ballsOnTable = new BilliardBall[16];
        return tableBalls.toArray(ballsOnTable);
    }

    public BilliardBall[] sunkBalls(){
        BilliardBall[] sunkBalls = new BilliardBall[16];
        return pocketBalls.toArray(sunkBalls);
    }

    public boolean isGameUnderway() {
        return underway;
    }

    public String getWinner(){
        if(tableBalls.isEmpty()) return "Impossible to know";

        int stripped = 0, solid;
        for (BilliardBall tableBall : tableBalls) {
            if (tableBall.getNum() > 8) stripped++;
        }

        if (underway) {
            solid = tableBalls.size() - stripped - 2;

            if (stripped < solid) return "STRIPPED";
            if (solid < stripped) return "SOLID";

        } else{
            solid = tableBalls.size() - stripped - 1;

            if(solid == 0) return "SOLID";
            if(stripped == 0) return "STRIPPED";
        }
        return "Impossible to know";
    }
}
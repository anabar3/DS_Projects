package e4;

import e2.ImmutableMatrix;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BilliardTableTest {
    public static BilliardTable table = new BilliardTable();
    public static BilliardTable[] nullArray = new BilliardTable[16];


    @Test
    void testConstructorLists() {
        table = new BilliardTable();
        assertArrayEquals(nullArray, table.ballsOnTable());
        assertArrayEquals(BilliardBall.values(), table.sunkBalls());
    }

    @Test
    void testStartGame(){
        table.startGame();
        assertArrayEquals(BilliardBall.values(), table.ballsOnTable());
        assertArrayEquals(nullArray, table.sunkBalls());
    }

    @Test
    void testUnderway(){
        table = new BilliardTable();
        assertFalse(table.isGameUnderway());
        table.startGame();
        assertTrue(table.isGameUnderway());
    }

    @Test
    void testPocketBall(){
        ArrayList<BilliardBall> sampleTable = new ArrayList<>(Arrays.asList(BilliardBall.values()));
        ArrayList<BilliardBall> samplePocket = new ArrayList<>();

        table = new BilliardTable();
        assertThrows(IllegalStateException.class, () -> table.pocketBall(BilliardBall.BALL2));
        table.startGame();

        table.pocketBall(BilliardBall.BALL2);
        samplePocket.add(BilliardBall.BALL2);
        sampleTable.remove(BilliardBall.BALL2);
        assertArrayEquals(samplePocket.toArray(new BilliardBall[16]), table.sunkBalls());
        assertArrayEquals(sampleTable.toArray(new BilliardBall[16]), table.ballsOnTable());

        table.pocketBall(BilliardBall.CUEBALL);
        assertArrayEquals(samplePocket.toArray(new BilliardBall[16]), table.sunkBalls());
        assertArrayEquals(sampleTable.toArray(new BilliardBall[16]), table.ballsOnTable());

        table.pocketBall(BilliardBall.BALL8);
        samplePocket.add(BilliardBall.BALL8);
        sampleTable.remove(BilliardBall.BALL8);
        assertFalse(table.isGameUnderway());
        assertArrayEquals(samplePocket.toArray(new BilliardBall[16]), table.sunkBalls());
        assertArrayEquals(sampleTable.toArray(new BilliardBall[16]), table.ballsOnTable());
    }

    @Test
    void testWinner(){
        table = new BilliardTable();
        assertEquals("Impossible to know", table.getWinner());
        table.startGame();
        assertEquals("Impossible to know", table.getWinner());
        table.pocketBall(BilliardBall.BALL3);
        assertEquals("SOLID", table.getWinner());
        table.pocketBall(BilliardBall.BALL10);
        table.pocketBall(BilliardBall.BALL11);
        assertEquals("STRIPPED", table.getWinner());
        table.pocketBall(BilliardBall.BALL8);
        assertEquals("Impossible to know", table.getWinner());
    }
}
package e4;

public enum BilliardBall {
    CUEBALL (0, "WHITE", "SOLID"),
    BALL1 (1, "YELLOW", "SOLID"),
    BALL2 (2, "BLUE", "SOLID"),
    BALL3 (3, "RED", "SOLID"),
    BALL4 (4, "PURPLE", "SOLID"),
    BALL5 (5, "ORANGE", "SOLID"),
    BALL6 (6, "GREEN", "SOLID"),
    BALL7 (7, "MAROON", "SOLID"),
    BALL8 (8, "BLACK", "SOLID"),
    BALL9 (9, "YELLOW", "STRIPED"),
    BALL10 (10, "BLUE", "STRIPED"),
    BALL11 (11, "RED", "STRIPED"),
    BALL12 (12, "PURPLE", "STRIPED"),
    BALL13 (13, "ORANGE", "STRIPED"),
    BALL14 (14, "GREEN", "STRIPED"),
    BALL15 (15, "MAROON", "STRIPED");

    final private int num;
    final private String color;
    final private String type;


    BilliardBall(int num, String color, String type) {
        this.num =num;
        this.color=color;
        this.type=type;
    }

    public int getNum(){
        return num;
    }


}

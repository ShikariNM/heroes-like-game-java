import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import units.BaseCharacter;
import units.Team;

public class Main {

    public static ArrayList<BaseCharacter> team1;
    public static ArrayList<BaseCharacter> team2;
    public static ArrayList<BaseCharacter> allTeam = new ArrayList<>();

    public static void main(String[] args) {
        
            Team leftSide = new Team(10, true);
            Team rightSide = new Team(10, false);

            leftSide.addRivals(rightSide);
            rightSide.addRivals(leftSide);

            team1 = leftSide.team;
            team2 = rightSide.team;

            allTeam.addAll(team1);
            allTeam.addAll(team2);

            Collections.sort(allTeam, Collections.reverseOrder());

            Scanner in = new Scanner(System.in);
            boolean game = true;
            while(game) {
                for (BaseCharacter character : allTeam) {
                    character.step();
                    if (leftSide.isWinner() || rightSide.isWinner()) {
                        game = false;
                        break;
                    }
                }
                View.view();
                in.nextLine();
            }
            in.close();
        
    }
}


                // MaxHP    defence speed   attack  damageDispersion    attackRange initiative  Ability points  Ability points+
// BaseChar        100      5       1       10      1                   1           50          100             *

// WARRIOR         +10      +1      +0      +1      -                   +0          0           max = 100       10
// Peasant         +5       +0      +1      +0      0                   +0          +5 (55)     start +20       +5
// Spearman        +10      +1      +0      +1      1                   +1          -5 (45)     start 0         +0

// ROGE            -10      -1      +0      +2      -                   +0          +5          100             +5
// Archer          -10      -2      +0      +0      2                   +7          +10(65)     +0              +0
// Crossbowman     +0       +0      +0      +1      1                   +5          +5 (60)     +10             +0

// Bandit          -15      -2      +2      +2      0                   +0          +20(75)     +0              +0          

// CASTER          -20      -2      +0      +0      -                   +0          +10         100             -
// Enchanter       +0       -1      +0      -2      0                   +14         +5 (65)     +0              -
// Monk            +5       +0      +0      +2      0                   +0          +10(70)     +20             -

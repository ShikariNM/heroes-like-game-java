package units;

import java.util.ArrayList;
import java.util.Random;


public class Team {
    public int size;
    public ArrayList<BaseCharacter> team;
    public Team rivals;
    public String name = "Right side";

    public Team(int size, boolean leftSide) {
        this.size = size;
        this.team = new ArrayList<>();
        if (leftSide) name = "Left side";
        for (int i = 0; i < size; i++) {
            BaseCharacter character;
            int option = new Random().nextInt(4);
            switch (option) {
            case 0:
                if (leftSide) character = new Peasant();
                else character = new Spearman();
                break;
            case 1:
                if (leftSide) character = new Archer();
                else character = new Crossbowman();
                break;
            case 2:
                if (leftSide) character = new Enchanter();
                else character = new Monk();
                break;
            default:
                character = new Bandit();
                break;
            }
            character.leftSide = leftSide;
            character.position = new Position(leftSide);
            character.name += "_" + this.name.charAt(0);
            team.add(character);
        }
        addAllias();
    }

    private void addAllias() {
        for (BaseCharacter character : this.team) {
            character.addAlliesOrRivals(false, this);
        }
    }

    public void addRivals(Team rivals) {
        this.rivals = rivals;
        for (BaseCharacter character : this.team) {
            character.addAlliesOrRivals(true, rivals);
        }
    }

    public boolean isWinner() {
        for (BaseCharacter rival : rivals.team) {
            if (rival.alive) return false;
        }
        System.out.println(this.name + " has won!");
        return true;
    }

    public void showTeam() {
        for (BaseCharacter character : this.team) {
            System.out.println(character.getInfo());
        }
    }
}

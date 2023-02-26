import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rover {
    private int[] position;
    private String facing;

    public Rover(String facing, int x, int y) {
        this.facing = facing;
        this.position = new int[]{x, y};
    }

    public String getFacing() {
        return facing;
    }

    public int[] getPosition() {
        return position;
    }

    public void go(String instructions) {
        instructions.chars().forEach((instruction) -> {
            execute((char) instruction);
        });
    }

    private void execute(char instruction)
    {
        Map<Character, Runnable> actions = new HashMap<>();
        actions.put('R', this::movement_to_right);
        actions.put('L', this::movement_to_left);
        actions.put('F', this::move_to_forward);
        actions.put('B', this::move_to_back);

        actions.get(instruction).run();
    }

    private void move_to_back() {
        if(facing == "N") {
            position[1] = position[1] - 1;
        }

        if(facing == "E"){
            position[0] = position[0] - 1;
        }

        if(facing == "S"){
            position[1] = position[1] + 1;
        }

        if(facing == "W"){
            position[0] = position[0] + 1;
        }
    }

    private void move_to_forward() {
        if(facing == "N") {
            position[1] = position[1] + 1;
        }

        if(facing == "E"){
            position[0] = position[0] + 1;
        }

        if(facing == "S"){
            position[1] = position[1] - 1;
        }

        if(facing == "W"){
            position[0] = position[0] - 1;
        }
    }

    private void movement_to_left() {
        turn(new String[]{"N", "W", "S", "E"});
    }

    private void movement_to_right()
    {
        turn(new String[]{"N", "E", "S", "W"});
    }

    private void turn(String[] compass) {
        int current = Arrays.asList(compass).indexOf(facing);
        facing = compass[(current + 1) % 4];
    }
}


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

    //Метод для отримання напряму руху марсоходу
    public String getFacing() {
        return facing;
    }

    //Метод для отримання позиції (координат) марсоходу
    public int[] getPosition() {
        return position;
    }

    //Метод для здійснення запиту на рух
    public void go(String instructions) {
        instructions.chars().forEach((instruction) -> {
            execute((char) instruction);
        });
    }

    //Метод для здійснення руху за отриманим запитом
    private void execute(char instruction)
    {
        Map<Character, Runnable> actions = new HashMap<>();
        actions.put('R', this::movement_to_right);
        actions.put('L', this::movement_to_left);
        actions.put('F', this::move_to_forward);
        actions.put('B', this::move_to_back);

        actions.get(instruction).run();
    }

    //Метод для здійснення руху назад
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

    //Метод для здійснення руху вперед
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

    //Метод для передачі запиту на поворот наліво
    private void movement_to_left() {
        turn(new String[]{"N", "W", "S", "E"});
    }

    //Метод для передачі запиту на поворот направо
    private void movement_to_right()
    {
        turn(new String[]{"N", "E", "S", "W"});
    }

    //Метод для здійснення повороту марсоходу
    private void turn(String[] compass) {
        int current = Arrays.asList(compass).indexOf(facing);
        facing = compass[(current + 1) % 4];
    }
}


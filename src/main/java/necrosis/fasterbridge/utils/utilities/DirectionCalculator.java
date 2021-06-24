package necrosis.fasterbridge.utils.utilities;

import necrosis.fasterbridge.arena.Direction;
import org.bukkit.entity.Player;

public class DirectionCalculator {

    public DirectionCalculator(){

    }

    public Direction getDirection(float yaw){
        if (yaw < 0) {
            yaw += 360;
        }
        if (yaw >= 315 || yaw < 45) {
            return Direction.SOUTH;
        } else if (yaw < 135) {
            return Direction.WEST;
        } else if (yaw < 225) {
            return Direction.NORTH;
        } else if (yaw < 315) {
            return Direction.EAST;
        }
        return Direction.NORTH;
    }
    public Direction getDirection(Player player){
        return this.getDirection(player.getLocation().getYaw());
    }
}

package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;

public class FallingState extends RabbitStateTemplate {
    @Override
    protected boolean doCheck(ChangeDescription.State state) {
        switch (state) {
            case RABBIT_FALLING:
            case RABBIT_FALLING_1:
            case RABBIT_FALLING_1_TO_DEATH:
            case RABBIT_DYING_OF_FALLING_2:
            case RABBIT_DYING_OF_FALLING:
            case RABBIT_FALLING_ONTO_LOWER_RIGHT:
            case RABBIT_FALLING_ONTO_RISE_RIGHT:
            case RABBIT_FALLING_ONTO_LOWER_LEFT:
            case RABBIT_FALLING_ONTO_RISE_LEFT:
            case RABBIT_FALLING_1_ONTO_LOWER_RIGHT:
            case RABBIT_FALLING_1_ONTO_RISE_RIGHT:
            case RABBIT_FALLING_1_ONTO_LOWER_LEFT:
            case RABBIT_FALLING_1_ONTO_RISE_LEFT:
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT:
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_RIGHT_2:
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT:
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_RIGHT_2:
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT:
            case RABBIT_DYING_OF_FALLING_SLOPE_RISE_LEFT_2:
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT:
            case RABBIT_DYING_OF_FALLING_2_SLOPE_RISE_LEFT_2:
                return true;
            default:
                return false;
        }
    }
}
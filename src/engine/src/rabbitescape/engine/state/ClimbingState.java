package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;

public class ClimbingState extends RabbitStateTemplate {
    @Override
    protected boolean doCheck(ChangeDescription.State state) {
        switch (state) {
            case RABBIT_ENTERING_EXIT_CLIMBING_RIGHT:
            case RABBIT_ENTERING_EXIT_CLIMBING_LEFT:
            case RABBIT_CLIMBING_LEFT_START:
            case RABBIT_CLIMBING_LEFT_CONTINUE_1:
            case RABBIT_CLIMBING_LEFT_CONTINUE_2:
            case RABBIT_CLIMBING_LEFT_END:
            case RABBIT_CLIMBING_LEFT_BANG_HEAD:
            case RABBIT_CLIMBING_RIGHT_START:
            case RABBIT_CLIMBING_RIGHT_CONTINUE_1:
            case RABBIT_CLIMBING_RIGHT_CONTINUE_2:
            case RABBIT_CLIMBING_RIGHT_END:
            case RABBIT_CLIMBING_RIGHT_BANG_HEAD:
                return true;
            default:
                return false;
        }
    }
}
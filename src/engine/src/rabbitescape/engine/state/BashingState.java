package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;

public class BashingState extends RabbitStateTemplate {
    @Override
    protected boolean doCheck(ChangeDescription.State state) {
        switch (state) {
            case RABBIT_BASHING_RIGHT:
            case RABBIT_BASHING_LEFT:
            case RABBIT_BASHING_UP_RIGHT:
            case RABBIT_BASHING_UP_LEFT:
            case RABBIT_BASHING_USELESSLY_RIGHT:
            case RABBIT_BASHING_USELESSLY_LEFT:
            case RABBIT_BASHING_USELESSLY_RIGHT_UP:
            case RABBIT_BASHING_USELESSLY_LEFT_UP:
                return true;
            default:
                return false;
        }
    }
}
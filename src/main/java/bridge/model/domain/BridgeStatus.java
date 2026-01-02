package bridge.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BridgeStatus {
    private final List<String> upBridge;
    private final List<String> downBridge;

    public BridgeStatus() {
        this.upBridge = new ArrayList<>();
        this.downBridge = new ArrayList<>();
    }

    public void addMove(String position, String result) {
        if (position.equals("U")) {
            upBridge.add(result);
            downBridge.add(" ");
            return;
        }
        downBridge.add(result);
        upBridge.add(" ");
    }

    public List<String> getUpBridge() {
        return Collections.unmodifiableList(upBridge);
    }

    public List<String> getDownBridge() {
        return Collections.unmodifiableList(downBridge);
    }
}

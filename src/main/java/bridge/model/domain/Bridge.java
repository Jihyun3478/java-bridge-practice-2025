package bridge.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bridge {
    private final List<String> positions;

    public Bridge(List<String> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public String getPosition(int index) {
        return positions.get(index);
    }

    public int size() {
        return positions.size();
    }
}

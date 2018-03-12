package io.dargenn.external;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private static final int X = 3;
    private static final int Y = 3;
    private List<Field> fields = new ArrayList<>();

    public Board() {
        prepareFields();
    }

    private void prepareFields() {
        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {
                fields.add(new Field(i, j, true, null));
            }
        }
    }

    public Field getFieldByXY(int x, int y) {
        return fields.stream().filter(f -> f.getX() == x && f.getY() == y).findFirst().orElse(null);
    }
}

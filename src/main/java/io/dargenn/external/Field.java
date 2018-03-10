package io.dargenn.external;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private int x;
    private int y;
    private boolean isEmpty;
    private TicTacToeType ticTacToeType;
}

package com.sagrada.ppp.cards;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;

import static com.sagrada.ppp.utils.StaticValues.NUMBER_OF_CELLS;

public class TestPanels {

    /**
     * @return panel with: no color duplication in the same row, every color used, no 5 & 6 value
     */
    public static WindowPanel panel_1_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0,new Dice(Color.BLUE, 1));
        panel.addDice(1,new Dice(Color.GREEN, 2));
        panel.addDice(2,new Dice(Color.PURPLE, 1));
        panel.addDice(3,new Dice(Color.RED, 2));
        panel.addDice(4,new Dice(Color.YELLOW, 1));

        panel.addDice(5,new Dice(Color.GREEN, 3));
        panel.addDice(6,new Dice(Color.PURPLE, 4));
        panel.addDice(7,new Dice(Color.RED, 3));
        panel.addDice(8,new Dice(Color.YELLOW, 4));
        panel.addDice(9,new Dice(Color.BLUE, 3));

        panel.addDice(10,new Dice(Color.PURPLE, 1));
        panel.addDice(11,new Dice(Color.RED, 2));
        panel.addDice(12,new Dice(Color.YELLOW, 1));
        panel.addDice(13,new Dice(Color.BLUE, 2));
        panel.addDice(14,new Dice(Color.GREEN, 1));

        panel.addDice(15,new Dice(Color.RED, 3));
        panel.addDice(16,new Dice(Color.YELLOW, 4));
        panel.addDice(17,new Dice(Color.BLUE, 3));
        panel.addDice(18,new Dice(Color.GREEN, 4));
        panel.addDice(19,new Dice(Color.PURPLE, 3));

        return panel;
    }

    /**
     * @return panel with: first row with a color duplication, second row with a hole, all values used
     */
    public static WindowPanel panel_1_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 5));
        panel.addDice(1,new Dice(Color.GREEN, 6));
        panel.addDice(2, new Dice(Color.BLUE, 5));
        panel.addDice(3,new Dice(Color.GREEN, 6));
        panel.addDice(4, new Dice(Color.BLUE, 5));

        panel.addDice(6,new Dice(Color.YELLOW, 1));
        panel.addDice(7,new Dice(Color.GREEN, 2));
        panel.addDice(8,new Dice(Color.BLUE, 1));
        panel.addDice(9,new Dice(Color.RED, 2));

        panel.addDice(10,new Dice(Color.GREEN, 5));
        panel.addDice(11,new Dice(Color.BLUE, 6));
        panel.addDice(12,new Dice(Color.YELLOW, 5));
        panel.addDice(13,new Dice(Color.RED, 6));
        panel.addDice(14,new Dice(Color.PURPLE, 5));

        panel.addDice(15,new Dice(Color.PURPLE, 3));
        panel.addDice(16,new Dice(Color.GREEN, 4));
        panel.addDice(17,new Dice(Color.BLUE, 3));
        panel.addDice(18,new Dice(Color.YELLOW, 4));
        panel.addDice(19,new Dice(Color.RED, 3));

        return panel;
    }

    /**
     * @return panel with: no row with 5 different colors, 10 diagonally adjacent same color dice
     */
    public static WindowPanel panel_1_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 5));
        panel.addDice(1,new Dice(Color.GREEN, 6));
        panel.addDice(2, new Dice(Color.BLUE, 5));
        panel.addDice(3,new Dice(Color.GREEN, 6));
        panel.addDice(4, new Dice(Color.BLUE, 5));

        panel.addDice(5,new Dice(Color.PURPLE, 2));
        panel.addDice(6,new Dice(Color.YELLOW, 1));
        panel.addDice(7,new Dice(Color.GREEN, 2));
        panel.addDice(8,new Dice(Color.PURPLE, 1));
        panel.addDice(9,new Dice(Color.RED, 2));

        panel.addDice(10,new Dice(Color.GREEN, 5));
        panel.addDice(11,new Dice(Color.BLUE, 6));
        panel.addDice(12,new Dice(Color.YELLOW, 5));
        panel.addDice(13,new Dice(Color.GREEN, 6));
        panel.addDice(14,new Dice(Color.YELLOW, 5));

        panel.addDice(15,new Dice(Color.PURPLE, 3));
        panel.addDice(16,new Dice(Color.GREEN, 4));
        panel.addDice(17,new Dice(Color.RED, 3));
        panel.addDice(18,new Dice(Color.YELLOW, 4));
        panel.addDice(19,new Dice(Color.RED, 3));

        return panel;
    }

    /**
     * @return panel with: no color duplication in the same column, every color used, all value used
     */
    public static WindowPanel panel_2_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(5,new Dice(Color.GREEN, 2));
        panel.addDice(10, new Dice(Color.RED, 1));
        panel.addDice(15,new Dice(Color.PURPLE, 2));

        panel.addDice(1, new Dice(Color.GREEN, 3));
        panel.addDice(6,new Dice(Color.YELLOW, 4));
        panel.addDice(11,new Dice(Color.PURPLE, 3));
        panel.addDice(16,new Dice(Color.RED, 4));

        panel.addDice(2,new Dice(Color.BLUE, 5));
        panel.addDice(7,new Dice(Color.GREEN, 6));
        panel.addDice(12,new Dice(Color.RED, 5));
        panel.addDice(17,new Dice(Color.PURPLE, 6));

        panel.addDice(3,new Dice(Color.YELLOW, 1));
        panel.addDice(8,new Dice(Color.BLUE, 3));
        panel.addDice(13,new Dice(Color.GREEN, 1));
        panel.addDice(18,new Dice(Color.RED, 3));

        panel.addDice(4,new Dice(Color.GREEN, 2));
        panel.addDice(9,new Dice(Color.RED, 4));
        panel.addDice(14,new Dice(Color.YELLOW, 2));
        panel.addDice(19,new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: 1 column with hole, 2 columns with repeated colors
     */
    public static WindowPanel panel_2_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(5,new Dice(Color.GREEN, 2));
        panel.addDice(10, new Dice(Color.RED, 1));
        panel.addDice(15,new Dice(Color.BLUE, 2));

        panel.addDice(1, new Dice(Color.GREEN, 3));
        panel.addDice(6,new Dice(Color.YELLOW, 4));
        panel.addDice(11,new Dice(Color.PURPLE, 3));
        panel.addDice(16,new Dice(Color.RED, 4));

        panel.addDice(2,new Dice(Color.BLUE, 5));
        panel.addDice(7,new Dice(Color.GREEN, 6));
        panel.addDice(12,new Dice(Color.RED, 5));
        panel.addDice(17,new Dice(Color.PURPLE, 6));

        panel.addDice(3,new Dice(Color.YELLOW, 1));
        panel.addDice(8,new Dice(Color.BLUE, 3));
        panel.addDice(13,new Dice(Color.GREEN, 1));
        panel.addDice(18,new Dice(Color.RED, 3));

        panel.addDice(4,new Dice(Color.GREEN, 2));
        panel.addDice(14,new Dice(Color.YELLOW, 2));
        panel.addDice(19,new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: no column with 4 different colors and 4 different values,
     *         6 diagonally adjacent same color dice
     */
    public static WindowPanel panel_2_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(5,new Dice(Color.GREEN, 2));
        panel.addDice(10, new Dice(Color.RED, 1));
        panel.addDice(15,new Dice(Color.BLUE, 2));

        panel.addDice(1, new Dice(Color.GREEN, 3));
        panel.addDice(6,new Dice(Color.YELLOW, 4));
        panel.addDice(16,new Dice(Color.RED, 4));

        panel.addDice(2,new Dice(Color.BLUE, 5));
        panel.addDice(7,new Dice(Color.GREEN, 6));
        panel.addDice(12,new Dice(Color.BLUE, 5));
        panel.addDice(17,new Dice(Color.PURPLE, 6));

        panel.addDice(3,new Dice(Color.YELLOW, 1));
        panel.addDice(13,new Dice(Color.GREEN, 1));
        panel.addDice(18,new Dice(Color.RED, 3));

        panel.addDice(4,new Dice(Color.GREEN, 2));
        panel.addDice(14,new Dice(Color.YELLOW, 2));
        panel.addDice(19,new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: no value duplication in the same row
     */
    public static WindowPanel panel_3_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1,new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.BLUE, 3));
        panel.addDice(3,new Dice(Color.GREEN, 4));
        panel.addDice(4, new Dice(Color.BLUE, 5));

        panel.addDice(5,new Dice(Color.RED, 2));
        panel.addDice(6,new Dice(Color.YELLOW, 3));
        panel.addDice(7,new Dice(Color.GREEN, 4));
        panel.addDice(8,new Dice(Color.BLUE, 5));
        panel.addDice(9,new Dice(Color.RED, 6));

        panel.addDice(10,new Dice(Color.GREEN, 3));
        panel.addDice(11,new Dice(Color.BLUE, 4));
        panel.addDice(12,new Dice(Color.YELLOW, 5));
        panel.addDice(13,new Dice(Color.RED, 6));
        panel.addDice(14,new Dice(Color.PURPLE, 1));

        panel.addDice(15,new Dice(Color.PURPLE, 4));
        panel.addDice(16,new Dice(Color.GREEN, 5));
        panel.addDice(17,new Dice(Color.BLUE, 6));
        panel.addDice(18,new Dice(Color.YELLOW, 1));
        panel.addDice(19,new Dice(Color.RED, 2));

        return panel;
    }

    /**
     * @return panel with: 1 ROW with hole, 1 ROW with color duplication
     */
    public static WindowPanel panel_3_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1,new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.BLUE, 3));
        panel.addDice(3,new Dice(Color.GREEN, 4));
        panel.addDice(4, new Dice(Color.BLUE, 5));

        panel.addDice(6,new Dice(Color.YELLOW, 3));
        panel.addDice(7,new Dice(Color.GREEN, 4));
        panel.addDice(8,new Dice(Color.BLUE, 5));
        panel.addDice(9,new Dice(Color.RED, 6));

        panel.addDice(10,new Dice(Color.GREEN, 3));
        panel.addDice(11,new Dice(Color.BLUE, 4));
        panel.addDice(12,new Dice(Color.YELLOW, 3));
        panel.addDice(13,new Dice(Color.RED, 6));
        panel.addDice(14,new Dice(Color.PURPLE, 1));

        panel.addDice(15,new Dice(Color.PURPLE, 4));
        panel.addDice(16,new Dice(Color.GREEN, 5));
        panel.addDice(17,new Dice(Color.BLUE, 6));
        panel.addDice(18,new Dice(Color.YELLOW, 1));
        panel.addDice(19,new Dice(Color.RED, 2));

        return panel;
    }

    /**
     * @return panel with: no row with 5 different value, 1 set of one of each value, 2 sets of one of each color
     */
    public static WindowPanel panel_3_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1,new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.BLUE, 3));
        panel.addDice(3,new Dice(Color.GREEN, 4));
        panel.addDice(4, new Dice(Color.BLUE, 3));

        panel.addDice(6,new Dice(Color.YELLOW, 3));
        panel.addDice(7,new Dice(Color.GREEN, 4));
        panel.addDice(8,new Dice(Color.BLUE, 5));
        panel.addDice(9,new Dice(Color.RED, 6));

        panel.addDice(10,new Dice(Color.GREEN, 3));
        panel.addDice(11,new Dice(Color.BLUE, 4));
        panel.addDice(12,new Dice(Color.YELLOW, 3));
        panel.addDice(13,new Dice(Color.RED, 6));
        panel.addDice(14,new Dice(Color.PURPLE, 1));

        panel.addDice(15,new Dice(Color.PURPLE, 4));
        panel.addDice(17,new Dice(Color.BLUE, 6));
        panel.addDice(18,new Dice(Color.YELLOW, 1));
        panel.addDice(19,new Dice(Color.RED, 2));

        return panel;
    }

    /**
     * @return panel with: every column with no value duplication, 2 sets of one of each value,
     *         13 diagonally adjacent same color dice, 3 sets of one of each color
     */
    public static WindowPanel panel_4_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(5,new Dice(Color.GREEN, 2));
        panel.addDice(10, new Dice(Color.RED, 3));
        panel.addDice(15,new Dice(Color.PURPLE, 4));

        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(6,new Dice(Color.YELLOW, 3));
        panel.addDice(11,new Dice(Color.PURPLE, 4));
        panel.addDice(16,new Dice(Color.RED, 5));

        panel.addDice(2,new Dice(Color.BLUE, 3));
        panel.addDice(7,new Dice(Color.GREEN, 4));
        panel.addDice(12,new Dice(Color.RED, 5));
        panel.addDice(17,new Dice(Color.PURPLE, 6));

        panel.addDice(3,new Dice(Color.YELLOW, 1));
        panel.addDice(8,new Dice(Color.BLUE, 3));
        panel.addDice(13,new Dice(Color.GREEN, 4));
        panel.addDice(18,new Dice(Color.RED, 2));

        panel.addDice(4,new Dice(Color.GREEN, 2));
        panel.addDice(9,new Dice(Color.RED, 4));
        panel.addDice(14,new Dice(Color.YELLOW, 5));
        panel.addDice(19,new Dice(Color.PURPLE, 6));

        return panel;
    }

    /**
     * @return panel with: 1 col with hole, 2 with repeated value, 12 diagonally adjacent same color dice
     */
    public static WindowPanel panel_4_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(5,new Dice(Color.GREEN, 2));
        panel.addDice(10, new Dice(Color.RED, 3));
        panel.addDice(15,new Dice(Color.BLUE, 1));

        panel.addDice(1, new Dice(Color.GREEN, 3));
        panel.addDice(6,new Dice(Color.YELLOW, 4));
        panel.addDice(11,new Dice(Color.PURPLE, 5));
        panel.addDice(16,new Dice(Color.RED, 6));

        panel.addDice(2,new Dice(Color.BLUE, 6));
        panel.addDice(7,new Dice(Color.GREEN, 5));
        panel.addDice(12,new Dice(Color.RED, 4));
        panel.addDice(17,new Dice(Color.PURPLE, 3));

        panel.addDice(3,new Dice(Color.YELLOW, 1));
        panel.addDice(8,new Dice(Color.BLUE, 3));
        panel.addDice(13,new Dice(Color.GREEN, 1));
        panel.addDice(18,new Dice(Color.RED, 2));

        panel.addDice(4,new Dice(Color.GREEN, 2));
        panel.addDice(14,new Dice(Color.YELLOW, 3));
        panel.addDice(19,new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: no column with 4 different colors and 4 different values
     */
    public static WindowPanel panel_4_2() {
        return TestPanels.panel_2_2();
    }

    /**
     * @return panel with: 10 sets of value 1 & 2
     */
    public static WindowPanel panel_5_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.PURPLE, 1));
        panel.addDice(3, new Dice(Color.RED, 2));
        panel.addDice(4, new Dice(Color.YELLOW, 1));

        panel.addDice(5, new Dice(Color.GREEN, 2));
        panel.addDice(6, new Dice(Color.PURPLE, 1));
        panel.addDice(7, new Dice(Color.RED, 2));
        panel.addDice(8, new Dice(Color.YELLOW, 1));
        panel.addDice(9, new Dice(Color.BLUE, 2));

        panel.addDice(10, new Dice(Color.PURPLE, 1));
        panel.addDice(11, new Dice(Color.RED, 2));
        panel.addDice(12, new Dice(Color.YELLOW, 1));
        panel.addDice(13, new Dice(Color.BLUE, 2));
        panel.addDice(14, new Dice(Color.GREEN, 1));

        panel.addDice(15, new Dice(Color.RED, 2));
        panel.addDice(16, new Dice(Color.YELLOW, 1));
        panel.addDice(17, new Dice(Color.BLUE, 2));
        panel.addDice(18, new Dice(Color.GREEN, 1));
        panel.addDice(19, new Dice(Color.PURPLE, 2));

        return panel;
    }

    /**
     * @return panel with: 5 sets of value 1 & 2
     */
    public static WindowPanel panel_5_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(6, new Dice(Color.PURPLE, 2));
        panel.addDice(2, new Dice(Color.PURPLE, 2));
        panel.addDice(8, new Dice(Color.YELLOW, 1));
        panel.addDice(4, new Dice(Color.YELLOW, 1));

        panel.addDice(10, new Dice(Color.PURPLE, 2));
        panel.addDice(16, new Dice(Color.YELLOW, 1));
        panel.addDice(12, new Dice(Color.YELLOW, 1));
        panel.addDice(18, new Dice(Color.GREEN, 2));
        panel.addDice(14, new Dice(Color.GREEN, 2));

        return panel;
    }

    /**
     * @return panel with: 9 sets of value 1 & 2
     */
    public static WindowPanel panel_5_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 5));
        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.PURPLE, 1));
        panel.addDice(3, new Dice(Color.RED, 2));
        panel.addDice(4, new Dice(Color.YELLOW, 1));

        panel.addDice(5, new Dice(Color.GREEN, 2));
        panel.addDice(6, new Dice(Color.PURPLE, 1));
        panel.addDice(7, new Dice(Color.RED, 2));
        panel.addDice(8, new Dice(Color.YELLOW, 1));
        panel.addDice(9, new Dice(Color.BLUE, 2));

        panel.addDice(10, new Dice(Color.PURPLE, 1));
        panel.addDice(11, new Dice(Color.RED, 2));
        panel.addDice(12, new Dice(Color.YELLOW, 1));
        panel.addDice(13, new Dice(Color.BLUE, 2));
        panel.addDice(14, new Dice(Color.GREEN, 1));

        panel.addDice(15, new Dice(Color.RED, 2));
        panel.addDice(16, new Dice(Color.YELLOW, 1));
        panel.addDice(17, new Dice(Color.BLUE, 2));
        panel.addDice(18, new Dice(Color.GREEN, 1));
        panel.addDice(19, new Dice(Color.PURPLE, 2));

        return panel;
    }

    /**
     * @return panel with: 10 sets of value 3 & 4
     */
    public static WindowPanel panel_6_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 3));
        panel.addDice(1, new Dice(Color.GREEN, 4));
        panel.addDice(2, new Dice(Color.PURPLE, 3));
        panel.addDice(3, new Dice(Color.RED, 4));
        panel.addDice(4, new Dice(Color.YELLOW, 3));

        panel.addDice(5, new Dice(Color.GREEN, 4));
        panel.addDice(6, new Dice(Color.PURPLE, 3));
        panel.addDice(7, new Dice(Color.RED, 4));
        panel.addDice(8, new Dice(Color.YELLOW, 3));
        panel.addDice(9, new Dice(Color.BLUE, 4));

        panel.addDice(10, new Dice(Color.PURPLE, 3));
        panel.addDice(11, new Dice(Color.RED, 4));
        panel.addDice(12, new Dice(Color.YELLOW, 3));
        panel.addDice(13, new Dice(Color.BLUE, 4));
        panel.addDice(14, new Dice(Color.GREEN, 3));

        panel.addDice(15, new Dice(Color.RED, 4));
        panel.addDice(16, new Dice(Color.YELLOW, 3));
        panel.addDice(17, new Dice(Color.BLUE, 4));
        panel.addDice(18, new Dice(Color.GREEN, 3));
        panel.addDice(19, new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: 5 sets of value 3 & 4
     */
    public static WindowPanel panel_6_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 3));
        panel.addDice(6, new Dice(Color.PURPLE, 4));
        panel.addDice(2, new Dice(Color.PURPLE, 4));
        panel.addDice(8, new Dice(Color.YELLOW, 3));
        panel.addDice(4, new Dice(Color.YELLOW, 3));

        panel.addDice(10, new Dice(Color.PURPLE, 4));
        panel.addDice(16, new Dice(Color.YELLOW, 3));
        panel.addDice(12, new Dice(Color.YELLOW, 3));
        panel.addDice(18, new Dice(Color.GREEN, 4));
        panel.addDice(14, new Dice(Color.GREEN, 4));

        return panel;
    }

    /**
     * @return panel with: 9 sets of value 3 & 4
     */
    public static WindowPanel panel_6_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 6));
        panel.addDice(1, new Dice(Color.GREEN, 4));
        panel.addDice(2, new Dice(Color.PURPLE, 3));
        panel.addDice(3, new Dice(Color.RED, 4));
        panel.addDice(4, new Dice(Color.YELLOW, 3));

        panel.addDice(5, new Dice(Color.GREEN, 4));
        panel.addDice(6, new Dice(Color.PURPLE, 3));
        panel.addDice(7, new Dice(Color.RED, 4));
        panel.addDice(8, new Dice(Color.YELLOW, 3));
        panel.addDice(9, new Dice(Color.BLUE, 4));

        panel.addDice(10, new Dice(Color.PURPLE, 3));
        panel.addDice(11, new Dice(Color.RED, 4));
        panel.addDice(12, new Dice(Color.YELLOW, 3));
        panel.addDice(13, new Dice(Color.BLUE, 4));
        panel.addDice(14, new Dice(Color.GREEN, 3));

        panel.addDice(15, new Dice(Color.RED, 4));
        panel.addDice(16, new Dice(Color.YELLOW, 3));
        panel.addDice(17, new Dice(Color.BLUE, 4));
        panel.addDice(18, new Dice(Color.GREEN, 3));
        panel.addDice(19, new Dice(Color.PURPLE, 4));

        return panel;
    }

    /**
     * @return panel with: 10 sets of value 5 & 6, 4 sets of one of each color
     */
    public static WindowPanel panel_7_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 5));
        panel.addDice(1, new Dice(Color.GREEN, 6));
        panel.addDice(2, new Dice(Color.PURPLE, 5));
        panel.addDice(3, new Dice(Color.RED, 6));
        panel.addDice(4, new Dice(Color.YELLOW, 5));

        panel.addDice(5, new Dice(Color.GREEN, 6));
        panel.addDice(6, new Dice(Color.PURPLE, 5));
        panel.addDice(7, new Dice(Color.RED, 6));
        panel.addDice(8, new Dice(Color.YELLOW, 5));
        panel.addDice(9, new Dice(Color.BLUE, 6));

        panel.addDice(10, new Dice(Color.PURPLE, 5));
        panel.addDice(11, new Dice(Color.RED, 6));
        panel.addDice(12, new Dice(Color.YELLOW, 5));
        panel.addDice(13, new Dice(Color.BLUE, 6));
        panel.addDice(14, new Dice(Color.GREEN, 5));

        panel.addDice(15, new Dice(Color.RED, 6));
        panel.addDice(16, new Dice(Color.YELLOW, 5));
        panel.addDice(17, new Dice(Color.BLUE, 6));
        panel.addDice(18, new Dice(Color.GREEN, 5));
        panel.addDice(19, new Dice(Color.PURPLE, 6));

        return panel;
    }

    /**
     * @return panel with: 5 sets of value 5 & 6, 0 sets of one of each color
     */
    public static WindowPanel panel_7_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 5));
        panel.addDice(6, new Dice(Color.PURPLE, 6));
        panel.addDice(2, new Dice(Color.PURPLE, 6));
        panel.addDice(8, new Dice(Color.YELLOW, 5));
        panel.addDice(4, new Dice(Color.YELLOW, 5));

        panel.addDice(10, new Dice(Color.PURPLE, 6));
        panel.addDice(16, new Dice(Color.YELLOW, 5));
        panel.addDice(12, new Dice(Color.YELLOW, 5));
        panel.addDice(18, new Dice(Color.GREEN, 6));
        panel.addDice(14, new Dice(Color.GREEN, 6));

        return panel;
    }

    /**
     * @return panel with: 9 sets of value 5 & 6
     */
    public static WindowPanel panel_7_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1, new Dice(Color.GREEN, 6));
        panel.addDice(2, new Dice(Color.PURPLE, 5));
        panel.addDice(3, new Dice(Color.RED, 6));
        panel.addDice(4, new Dice(Color.YELLOW, 5));

        panel.addDice(5, new Dice(Color.GREEN, 6));
        panel.addDice(6, new Dice(Color.PURPLE, 5));
        panel.addDice(7, new Dice(Color.RED, 6));
        panel.addDice(8, new Dice(Color.YELLOW, 5));
        panel.addDice(9, new Dice(Color.BLUE, 6));

        panel.addDice(10, new Dice(Color.PURPLE, 5));
        panel.addDice(11, new Dice(Color.RED, 6));
        panel.addDice(12, new Dice(Color.YELLOW, 5));
        panel.addDice(13, new Dice(Color.BLUE, 6));
        panel.addDice(14, new Dice(Color.GREEN, 5));

        panel.addDice(15, new Dice(Color.RED, 6));
        panel.addDice(16, new Dice(Color.YELLOW, 5));
        panel.addDice(17, new Dice(Color.BLUE, 6));
        panel.addDice(18, new Dice(Color.GREEN, 5));
        panel.addDice(19, new Dice(Color.PURPLE, 6));

        return panel;
    }

    /**
     * @return panel with: 0 sets of one of each value, 11 diagonally adjacent same color dice,
     *         3 sets of one of each color
     */
    public static WindowPanel panel_8_0() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.RED, 1));
        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.BLUE, 1));
        panel.addDice(3, new Dice(Color.GREEN, 2));
        panel.addDice(4, new Dice(Color.PURPLE, 1));

        panel.addDice(5, new Dice(Color.YELLOW, 3));
        panel.addDice(6, new Dice(Color.BLUE, 4));
        panel.addDice(7, new Dice(Color.GREEN, 3));
        panel.addDice(8, new Dice(Color.PURPLE, 4));
        panel.addDice(9, new Dice(Color.RED, 3));

        panel.addDice(10, new Dice(Color.PURPLE, 1));
        panel.addDice(11, new Dice(Color.YELLOW, 2));
        panel.addDice(13, new Dice(Color.GREEN, 2));
        panel.addDice(14, new Dice(Color.BLUE, 1));

        panel.addDice(15, new Dice(Color.RED, 3));
        panel.addDice(16, new Dice(Color.BLUE, 4));
        panel.addDice(17, new Dice(Color.YELLOW, 3));
        panel.addDice(18, new Dice(Color.RED, 4));
        panel.addDice(19, new Dice(Color.YELLOW, 3));

        return  panel;
    }

    /**
     * @return panel with: no row with 5 different value, 1 set of one of each value
     */
    public static WindowPanel panel_8_1() {
        return TestPanels.panel_3_2();
    }

    /**
     * @return panel with: every column with no value duplication, 2 sets of one of each value
     */
    public static WindowPanel panel_8_2() {
        return TestPanels.panel_4_0();
    }

    /**
     * @return panel with: 3 sets of one of each value
     */
    public static WindowPanel panel_8_3() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.RED, 1));
        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.BLUE, 1));
        panel.addDice(3, new Dice(Color.GREEN, 2));
        panel.addDice(4, new Dice(Color.PURPLE, 6));

        panel.addDice(5, new Dice(Color.YELLOW, 3));
        panel.addDice(6, new Dice(Color.BLUE, 4));
        panel.addDice(7, new Dice(Color.GREEN, 3));
        panel.addDice(8, new Dice(Color.PURPLE, 4));
        panel.addDice(9, new Dice(Color.RED, 3));

        panel.addDice(10, new Dice(Color.PURPLE, 5));
        panel.addDice(11, new Dice(Color.YELLOW, 6));
        panel.addDice(13, new Dice(Color.GREEN, 5));
        panel.addDice(14, new Dice(Color.BLUE, 6));

        panel.addDice(15, new Dice(Color.RED, 1));
        panel.addDice(16, new Dice(Color.BLUE, 2));
        panel.addDice(17, new Dice(Color.YELLOW, 3));
        panel.addDice(18, new Dice(Color.RED, 4));
        panel.addDice(19, new Dice(Color.YELLOW, 5));

        return  panel;
    }

    /**
     * @return panel with: 0 sets of one of each value, 11 diagonally adjacent same color dice
     */
    public static WindowPanel panel_9_0() {
        return TestPanels.panel_8_0();
    }

    /**
     * @return panel with: 20 diagonally adjacent same color dice, 2 alternating colors in diagonal pattern
     */
    public static WindowPanel panel_9_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.RED, 1));
        panel.addDice(1, new Dice(Color.GREEN, 2));
        panel.addDice(2, new Dice(Color.RED, 1));
        panel.addDice(3, new Dice(Color.GREEN, 2));
        panel.addDice(4, new Dice(Color.RED, 1));

        panel.addDice(5, new Dice(Color.GREEN, 3));
        panel.addDice(6, new Dice(Color.RED, 4));
        panel.addDice(7, new Dice(Color.GREEN, 3));
        panel.addDice(8, new Dice(Color.RED, 4));
        panel.addDice(9, new Dice(Color.GREEN, 3));

        panel.addDice(10, new Dice(Color.RED, 1));
        panel.addDice(11, new Dice(Color.GREEN, 2));
        panel.addDice(12, new Dice(Color.RED, 1));
        panel.addDice(13, new Dice(Color.GREEN, 2));
        panel.addDice(14, new Dice(Color.RED, 1));

        panel.addDice(15, new Dice(Color.GREEN, 3));
        panel.addDice(16, new Dice(Color.RED, 4));
        panel.addDice(17, new Dice(Color.GREEN, 3));
        panel.addDice(18, new Dice(Color.RED, 4));
        panel.addDice(19, new Dice(Color.GREEN, 3));

        return panel;
    }

    /**
     * @return panel with: 10 diagonally adjacent same color dice, 1 alternating color in diagonal pattern
     */
    public static WindowPanel panel_9_2() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.RED, 1));

        panel.addDice(6, new Dice(Color.RED, 4));

        panel.addDice(2, new Dice(Color.RED, 1));

        panel.addDice(10, new Dice(Color.RED, 1));

        panel.addDice(16, new Dice(Color.RED, 4));

        panel.addDice(12, new Dice(Color.RED, 1));

        panel.addDice(8, new Dice(Color.RED, 4));

        panel.addDice(4, new Dice(Color.RED, 1));

        panel.addDice(14, new Dice(Color.RED, 1));

        panel.addDice(18, new Dice(Color.RED, 4));

        return panel;
    }

    /**
     * @return panel with: 0 diagonally adjacent same color dice, 2 colors in diamond pattern with blanks
     */
    public static WindowPanel panel_9_3() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.RED, 1));

        panel.addDice(6, new Dice(Color.BLUE, 4));

        panel.addDice(2, new Dice(Color.RED, 1));

        panel.addDice(10, new Dice(Color.RED, 1));

        panel.addDice(16, new Dice(Color.BLUE, 4));

        panel.addDice(12, new Dice(Color.RED, 1));

        panel.addDice(8, new Dice(Color.BLUE, 4));

        panel.addDice(4, new Dice(Color.RED, 1));

        panel.addDice(14, new Dice(Color.RED, 1));

        panel.addDice(18, new Dice(Color.BLUE, 4));

        return panel;
    }

    /**
     * @return panel with: no column with 4 different colors and 4 different values,
     *         6 diagonally adjacent same color dice
     */
    public static WindowPanel panel_9_4() {
        return TestPanels.panel_2_2();
    }

    /**
     * @return panel with: no row with 5 different colors, 10 diagonally adjacent same color dice
     */
    public static WindowPanel panel_9_5() {
        return TestPanels.panel_1_2();
    }

    /**
     * @return panel with: 1 col with hole, 2 with repeated value, 12 diagonally adjacent same color dice
     */
    public static WindowPanel panel_9_6() {
        return TestPanels.panel_4_1();
    }

    /**
     * @return panel with: every column with no value duplication, 2 sets of one of each value,
     *         13 diagonally adjacent same color dice
     */
    public static WindowPanel panel_9_7() {
        return TestPanels.panel_4_0();
    }

    /**
     * @return panel with: 0 sets of one of each value, 11 diagonally adjacent same color dice,
     *         3 sets of one of each color
     */
    public static WindowPanel panel_10_0() {
        return TestPanels.panel_8_0();
    }

    /**
     * @return panel with: 4 sets of one of each color
     */
    public static WindowPanel panel_10_1() {
        WindowPanel panel = createBlankPanel();

        panel.addDice(0, new Dice(Color.BLUE, 1));
        panel.addDice(1, new Dice(Color.PURPLE, 2));
        panel.addDice(2, new Dice(Color.GREEN, 1));
        panel.addDice(3, new Dice(Color.YELLOW, 2));
        panel.addDice(4, new Dice(Color.RED, 1));

        panel.addDice(5, new Dice(Color.RED, 3));
        panel.addDice(6, new Dice(Color.BLUE, 4));
        panel.addDice(7, new Dice(Color.PURPLE, 3));
        panel.addDice(8, new Dice(Color.GREEN, 4));
        panel.addDice(9, new Dice(Color.YELLOW, 3));

        panel.addDice(10, new Dice(Color.YELLOW, 1));
        panel.addDice(11, new Dice(Color.RED, 2));
        panel.addDice(12, new Dice(Color.BLUE, 1));
        panel.addDice(13, new Dice(Color.PURPLE, 2));
        panel.addDice(14, new Dice(Color.GREEN, 1));

        panel.addDice(15, new Dice(Color.RED, 3));
        panel.addDice(16, new Dice(Color.BLUE, 4));
        panel.addDice(17, new Dice(Color.PURPLE, 3));
        panel.addDice(18, new Dice(Color.GREEN, 4));
        panel.addDice(19, new Dice(Color.YELLOW, 3));

        return panel;
    }

    /**
     * @return panel with: every column with no value duplication, 2 sets of one of each value,
     *         13 diagonally adjacent same color dice, 3 sets of one of each color
     */
    public static WindowPanel panel_10_2() {
        return TestPanels.panel_4_0();
    }

    /**
     * @return panel with: 10 sets of value 5 & 6, 4 sets of one of each color
     */
    public static WindowPanel panel_10_3() {
        return TestPanels.panel_7_0();
    }

    /**
     * @return panel with: 5 sets of value 5 & 6, 0 sets of one of each color
     */
    public static WindowPanel panel_10_4() {
        return TestPanels.panel_7_1();
    }

    /**
     * @return panel with: no row with 5 different value, 1 set of one of each value, 2 sets of one of each color
     */
    public static WindowPanel panel_10_5() {
        return TestPanels.panel_3_2();
    }

    /**
     * @return useful panel for toolCard number 2 & 3
     */
    public static WindowPanel toolCardPanel() {
        WindowPanel panel = new WindowPanel(10, StaticValues.FRONT_SIDE);

        panel.addDice(2, new Dice(Color.GREEN, 3));

        panel.addDice(8, new Dice(Color.BLUE, 6));

        panel.addDice(13, new Dice(Color.PURPLE, 5));

        return panel;
    }

    /**
     * @return an empty windowPanel
     */
    public static WindowPanel createBlankPanel() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            cells.add(new Cell());
        }
        return new WindowPanel("blank", 0, 0, cells);
    }

}
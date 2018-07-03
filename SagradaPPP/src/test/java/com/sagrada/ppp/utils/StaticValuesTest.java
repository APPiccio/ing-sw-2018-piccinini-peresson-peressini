package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class StaticValuesTest {

    @Test
    public void test() {
        for(int i = 0; i <= 13 ; i++){
            StringBuilder stringBuilder = new StringBuilder();
            StaticValues.readConstants();
            stringBuilder.append(StaticValues.getToolCardDescription(i));
            stringBuilder.append(StaticValues.getAssetUri(i));
            stringBuilder.append(StaticValues.getPublicObjectiveCardDescription(i));
            for (Color color:Color.values()) {
                stringBuilder.append(StaticValues.getAssetUri(color));
                stringBuilder.append(StaticValues.getAssetUri(color,i));

            }


            System.out.println(stringBuilder.toString());

        }
    }
}
package com.deeep.sod2.utility;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/14/13
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        int onDuration = 0;
        int offDuration = 0;
        int totalDuration = 202;
        // 1100 1010
        offDuration = totalDuration & 0b11110000;
        offDuration = offDuration >> 4;
        onDuration = totalDuration & 0b00001111;
        System.out.println(onDuration + " " + offDuration);
    }
}

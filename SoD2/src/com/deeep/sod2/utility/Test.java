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
        ArrayList<String> names = new ArrayList<>();
        names.add("Elmar");
        names.add("Aran");
        names.add("Aran3");
        names.add("Elmar");
        names.add("Aran2");
        names.add("Elmar");
        ArrayList<String> elmarName = new ArrayList<>();
        ArrayList<String> removeList = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals("Elmar")) {
                removeList.add(names.get(i));
                elmarName.add(names.get(i));
            }
        }
        for (String string : names) {
            System.out.println(string);
        }
        for (int i = 0; i < removeList.size(); i++) {
            names.remove(removeList.get(i));
        }
        System.out.println("-----------------");
        for (String string : names) {
            System.out.println(string);
        }
        System.out.println("-----------------");
        for (int i = 0; i < elmarName.size(); i++) {
            names.add(0, elmarName.get(i));
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println(i + ": " + names.get(i));
        }
    }
}

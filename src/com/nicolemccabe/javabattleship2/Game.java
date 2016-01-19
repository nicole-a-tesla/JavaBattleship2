package com.nicolemccabe.javabattleship2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }


}
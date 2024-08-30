package com.skillnez.hangman.utils;

import java.util.ArrayList;
import java.util.List;

public class GraphicStorage {

    List<String> gallows = new ArrayList<>();

    GraphicStorage () {
        gallows.add(
                """ 
                ---------
                |       |
                |
                |
                |
                |
                |
           _____|_____
           """
        );

        gallows.add("""
                 ---------
                 |       |
                 |       0
                 |
                 |
                 |
                 |
            _____|_____
            """
        );
        gallows.add(
                """
                    ---------
                    |       |
                    |       0
                    |      / \\
                    |      \\ /
                    |
                    |
               _____|_____
               """
        );
        gallows.add(
                """
                ---------
                |       |
                |       0
                |   ---/ \\
                |      \\ /
                |
                |
           _____|_____
           """
        );
        gallows.add(
                """
                   ---------
                   |       |
                   |       0
                   |   ---/ \\---
                   |      \\ /
                   |
                   |
              _____|_____
              """
        );
        gallows.add(
                """
                  ---------
                  |       |
                  |       0
                  |   ---/ \\---
                  |      \\ /
                  |     /
                  |    /
             _____|_____
             """
        );
        gallows.add(
                """
                   ---------
                   |       |
                   |       0
                   |   ---/ \\---
                   |      \\ /
                   |     /   \\
                   |    /     \\
              _____|_____
              """
        );
    }

    public String getCondition(int index) {
        return gallows.get(index);
    }
}


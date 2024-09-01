package com.skillnez.hangman.utils;

import java.util.ArrayList;
import java.util.List;

public class GraphicStorage {

    private List<String> gallows = new ArrayList<>();

    public GraphicStorage () {
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

    protected String getCondition(int index) {
        return gallows.get(index);
    }
}


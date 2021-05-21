package com.fbproject;

// class yang digunakan untuk memanajemen text yang muncul pada layar game
public class TextInterface {
    // atribut score untuk mencatat skor yang didapat sapat permainan dimulai
    private int score;
    // atribut highscore untuk mencata skor tertinggi selama game dijalankan
    private int highScore;
    // atribut highscore untuk catatan saat game selesai
    private String gameOver;

    // constructor untuk membuat object class TestInterface
    public TextInterface() {
        score = 0;
        highScore = 0;
        gameOver = "Game Over";
    }

    // method untuk menambahkan nilai skor, pada saat objek tails
    // telah melewati sepasang objek pipes
    public void addScore(int value) {
        score = score + value;
    }

    // method untuk mereset skor yang didapatkan, jika mode permainan telah berakhir
    public void resetScore() {
        score = 0;
    }

    // method untuk mencatat skor tertinggi selama game tersebut berjalan
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    // method untuk mendapatkan nilai skor pada saat permainan berlangsung
    public int getScore() {
        //System.out.println("Tails Score : " + score);
        return score;
    }

    // method untuk mendapatkan nilai skor tertinggi pada saat game dijalankan 
    public int getHighScore() {
        return highScore;
    }

    // method untuk mendapatkan tulisan game over
    public String getGameOver() {
        return gameOver;
    }
    
}

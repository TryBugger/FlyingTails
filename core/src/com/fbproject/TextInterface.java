package com.fbproject;

// class yang digunakan untuk memanajemen text yang muncul pada layar game
public class TextInterface {
    // atribut score untuk mencatat skor yang didapat sapat permainan dimulai
    public static int score;
    // atribut highscore untuk mencata skor tertinggi selama game dijalankan
    public static int highScore;
    // atribut highscore untuk catatan saat game selesai
    public static String gameOver = "Game Over";
    // atribut menuText untuk tulisan menu utama
    public static String titleText = "Flying Tails";

    // method untuk mereset skor yang didapatkan, jika mode permainan telah berakhir
    //public void resetScore() {
    //    score = 0;
   // }

    // method untuk mendapatkan nilai skor tertinggi pada saat game dijalankan 
    public int getHighScore() {
        return highScore;
    }    
}

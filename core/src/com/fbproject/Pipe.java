package com.fbproject;

// import class dari library framework LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

// import library java untuk perhitungan random
import java.util.Random;

// pengolahan class Pipe yang diinherit oleh class Entity
public class Pipe extends Entity {
    // untuk mengatur lebar pipa / obstacle
    // lebar kayu
    private static final int PIPE_WIDTH = 70;
    // untuk mengatur jarak antar pipa pada saat keseluruhan pipa dispawn
    private static final int PIPE_SPACING = 175;
    // untuk mengatur tinggi minimal pipa pada saat pipa dispawn
    private static final int MIN_PIPE_HEIGHT = 140;
    // untuk mengatur total pipa yang dispawn
    private static final int PIPE_TOTAL = 4;
    // untuk mengatur jarak renggang antara pipa atas dengan pipa bawah
    // lebar lubang
    private static final int PIPE_GAP = 125;
    // untuk mengatur kecepatan pipa berpindah kearah kiri / tails muncul
    private static final int SPEED = 70;

    // membentuk sepasang pipa sebagai obstacle, dimana
    // index 0 untuk pipe bawah, serta index 1 untuk pipe atas
    private Sprite[] sPipe = new Sprite[2];

    // membentuk objek randomizer yang dapat menggunakan method 
    // untuk mendapatkan nilai random
    private Random randomizer = new Random();

    // menampung nilai tinggi dari pipa bawah
    private int pipeBottomHeight;

    // membentuk costructor yang menerima argument xPosition 
    // yang membentuk texture, sprite pipa atas dan bawah
    // serta menghitung ukuran tinggi pipa bawah
    public Pipe(float xPosition) {
        // membentuk objek tekstur yang menggunakan assets untuk objek pipa
        texture = new Texture("WoodObstacle.png");

        // menghitung tinggi pipa bawah dengan perhitungan :
        // minimal tinggi + angka random (0 hingga tinggi maksimal (pipa bawah - minimal tinggi))
        pipeBottomHeight = MIN_PIPE_HEIGHT + randomizer.nextInt(FlappyBird.HEIGHT - PIPE_GAP - 2 * MIN_PIPE_HEIGHT);

        // membentuk objek sprite yang digunakan dalam memodifikasi
        // bentuk tekstur dari objek pipa untuk pipa bawah
        sPipe[0] = new Sprite(texture);
        // mengatur ukuran pipa bawah dengan lebar berdasarkan parameter pipe_width
        // serta tinggi berdasarkan parameter pipeBottomHeight yang dihasilkan dari
        // perhitungan tinggi pipa bawah sebelumnya
        sPipe[0].setSize(PIPE_WIDTH, pipeBottomHeight);
        // mengatur posisi pipa bawah, dimana posisi x didapatkan dari argument constructor
        // xPosition, serta posisi y bernilai 0
        sPipe[0].setPosition(xPosition, 0);

        // membentuk objek sprite yang digunakan dalam memodifikasi
        // bentuk tekstur dari objek pipa untuk pipa atas
        sPipe[1] = new Sprite(texture);
        // mengatur ukuran pipa atas dengan lebar berdasarkan parameter pipe_width
        // serta tinggi berdasarkan perhitungan :
        // tinggi layar - jarak renggang antar pipa - tinggi pipa atas
        sPipe[1].setSize(PIPE_WIDTH, FlappyBird.HEIGHT - PIPE_GAP - pipeBottomHeight);
        // mengatur posisi pipa atas, dimana posisi x didapatkan dari argument constructor
        // xPosition, serta posisi y bernilai dari perhitungan :
        // jarak renggang antar pipa + tinggi pipa atas
        sPipe[1].setPosition(xPosition, PIPE_GAP + pipeBottomHeight);
        // mengatur titik putaran maupun scaling objek dari kiri atas objek (posisi x dan y objek)
        // keposisi tengah gambar untuk melakukan rotasi objek
        sPipe[1].setOrigin(sPipe[1].getWidth() / 2, sPipe[1].getHeight() / 2);
        // merotasi gambar sebesar 180 derajat
        sPipe[1].setRotation(180f);
    }

    // method override dari parent class entity, dimana digunakan untuk
    // mengatur perpindahaan posisi sepasang pipa, yaitu pipa atas dan pipa bawah
    @Override
    public void move() {
        // jika posisi sepasang pipa tersebut telah melewati batas layar kiri permainan,
        // maka posisi sepasang pipa akan dipindah terlebih dahulu keposisi kanan dari pipa paling kanan
        // dengan ukuran pipa yang berbeda
        if(sPipe[0].getX() + sPipe[0].getWidth() < 0) {
            // menghitung tinggi pipa bawah dengan perhitungan :
            // minimal tinggi + angka random (0 hingga tinggi maksimal (pipa bawah - minimal tinggi))
            pipeBottomHeight = MIN_PIPE_HEIGHT + randomizer.nextInt(FlappyBird.HEIGHT - PIPE_GAP - 2 * MIN_PIPE_HEIGHT);

            // mengubah ukuran pipa bawah dengan lebar berdasarkan parameter pipe_width
            // serta tinggi berdasarkan parameter pipeBottomHeight yang dihasilkan dari
            // perhitungan tinggi pipa bawah sebelumnya
            sPipe[0].setSize(PIPE_WIDTH, pipeBottomHeight);
            // mengubah posisi pipa bawah, dimana posisi x didapatkan dari
            // posisi x pipa + (jarak antar pipa + lebar pipa) * 4 (karena ingin memindahkan 
            // posisi pipa ke posisi terakhir dari sekumpulan pasang pipa), serta posisi y bernilai 0
            sPipe[0].setPosition(sPipe[0].getX() + (sPipe[0].getWidth() + PIPE_SPACING) * PIPE_TOTAL, 0);
            
            // mengubah ukuran pipa atas dengan lebar berdasarkan parameter pipe_width
            // serta tinggi berdasarkan perhitungan :
            // tinggi layar - jarak renggang antar pipa - tinggi pipa atas
            sPipe[1].setSize(PIPE_WIDTH, FlappyBird.HEIGHT - PIPE_GAP - pipeBottomHeight);
            // mengubah posisi pipa atas, dimana posisi x didapatkan dari 
            // posisi x pipa + (jarak antar pipa + lebar pipa) * 4 (karena ingin memindahkan 
            // posisi pipa ke posisi terakhir dari sekumpulan pasang pipa),
            // serta posisi y bernilai dari perhitungan :
            // jarak renggang antar pipa + tinggi pipa atas
            sPipe[1].setPosition(sPipe[1].getX() + (sPipe[1].getWidth() + PIPE_SPACING) * PIPE_TOTAL, PIPE_GAP + pipeBottomHeight);
            // mengubah titik putaran maupun scaling objek dari kiri atas objek (posisi x dan y objek)
            // keposisi tengah gambar dengan ukuran lebar dan tinggi yang berbeda
            // untuk menentukan ulang titik tengah rotasi dan scaling gambar
            sPipe[1].setOrigin(sPipe[1].getWidth() / 2, sPipe[1].getHeight() / 2);
        }

        // dari posisi pipa tersebut (baik telah dipindah ulang maupun belum),
        // akan memindah posisi x dengan mengurangi dari nilai x sebelumnya dengan nilai
        // kecepatan geser pipa * waktu proses game berjalan, dengan posisi y tetap
        sPipe[0].setPosition(sPipe[0].getX() - (SPEED * Gdx.graphics.getDeltaTime()), sPipe[0].getY());
        sPipe[1].setPosition(sPipe[1].getX() - (SPEED * Gdx.graphics.getDeltaTime()), sPipe[1].getY());
    }

     // method turunan entity untuk menghilangkan atau menghancurkan objek pipe
    @Override
    public void despawn() {
        texture.dispose();
    }

    // getter untuk mendapatkan sprite pipe dari array sprite
    // berdasarkan index yang diberikan pada argument
    public Sprite getsPipeSide(int index) {
        return sPipe[index];
    }

    // getter untuk mendapatkan nilai jarak antar pipa
    public static int getPipeSpacing() {
        return PIPE_SPACING;
    }

    // getter untuk mendapatkan lebar pipa
    public static int getPipeWidth() {
        return PIPE_WIDTH;
    }

    // getter untuk mendapatkan total pipe yang dibuat
    public static int getPipeTotal() {
        return PIPE_TOTAL;
    }
}

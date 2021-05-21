package com.fbproject;

// import class dari library framework LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

// pengolahan class Tails yang diinherit oleh class Entity
public class Tails extends Entity {
    // digunakan untuk kecepatan gravitasi tails pada saat gerakan default
    // dengan nilai // 175 frame per pixel
    private static final float GRAVITY = 175f; 
    // digunakan untuk mengurangi ukuran hitbox tails dari ukuran objek semula
    // dengan mengurangi ukuran sebesar 8 pixel
    private static final float HITBOX_REDUCER = 8f;
    
    // digunakan untuk mendapatkan sprite dari texture yang digunakan oleh tails
    // untuk memodifikasi objek dengan method-method yang disediakan class Sprite
    private Sprite sprite;

    // digunakan untuk menentukan kecepatan objek tails, dimana mengubah posisi pemain
    // dengan menambahkan nilai yang telah ditentukan
    private float speed;

    // constructor untuk class tails, yang menerima argument posisi x dan y objek,
    // yang membentuk objek texture dan sprite
    public Tails(float xPosition, float yPosition) {
        // texture digunakan untuk mendapatkan asset tails yang digunakan pada objek
        texture = new Texture("Tails.png");
        // sprite digunakan untuk menggunakan teksture asset dari objek tail
        // agar dapat dimodifikasi teksturenya
        sprite = new Sprite(texture);
        // mengubah ukuran objek menjadi 70 * 70 pixel
        sprite.setSize(70, 70);
        // menentukan posisi awal objek tails
        sprite.setPosition(xPosition - (sprite.getWidth() / 2), yPosition - (sprite.getHeight() / 2));
    }

    // method override dari parent class entity, dimana digunakan untuk
    // mengatur pergerakan serta kecepatan objek tails
    @Override
    public void move() {       
        // kondisi jika game menerima input tab pada android, klik pada mouse, ataupun 
        // spasi pada keybard dimana tombol-tombol tersebut sedang ditekan
        if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // jika posisi atas objek tails menyentuh batas atas latar permainan,
            // maka kecepatan objek menjadi 0 yang memebuat objek tidak bergerak
            if(sprite.getY() + sprite.getHeight() >= FlappyBird.HEIGHT) {
                speed = 0;
            // jika tidak meneyentuh batas atas latar permainan, maka kecepatan
            // bernilai terbalik dengan nilai gravitasi, yang menaikkan objek keatas
            } else {
                speed = GRAVITY * -1;
            }   
        // kondisi jika game tidak menerima ketiga jenis input tersebut         
        } else {
            // jika posisi bawah objek tails menyentuh batas bawah latar permainan,
            // maka kecepatan objek menjadi 0 yang memebuat objek tidak bergerak
            if(sprite.getY() <= 0) {
                speed = 0;
            // jika tidak meneyentuh batas bawah latar permainan, maka kecepatan
            // bernilai gravitasi, yang mengarahkan objek kebawah
            } else {
                speed = GRAVITY;
            }
        }        

        // memindahkan posisi y objek tails berdasarkan nilai y objek yang dikurangi
        // dengan nilai dari speed * waktu proses game berjalan
        sprite.setY(sprite.getY() - (speed * Gdx.graphics.getDeltaTime()));
    }

    // method turunan entity untuk menghilangkan atau menghancurkan objek tails
    @Override
    public void despawn() {
        texture.dispose();
    }

    // getter untuk mendapatkan nilai sprite
    public Sprite getSprite() {
        return sprite;
    }

    // getter untuk hitbox_reducer yang berguna untuk mengecilkan hitbox
    // objek tails dari ukuran semula objek
    public static float getHitboxReducer() {
        return HITBOX_REDUCER;
    }
}

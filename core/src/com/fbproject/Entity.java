package com.fbproject;

// import class dari library framework LibGDX
import com.badlogic.gdx.graphics.Texture;

// pembuatan class abstract entity yang akan digunakan atau menginherit
// kepada class Tails dan Java, yang terdiri dari :
abstract class Entity {
    // objek texture yang digunakan di child class 
    // untuk mendapatkan teksture class tersebut
    protected Texture texture;

    // method abstract move yang digunakan di child class 
    // untuk menggerakkan objek child dari entity
    protected abstract void move();
    // method abstract despawn yang digunakan di child class 
    // untuk menghilangkan atau menghancurkan objek child dari entity
    protected abstract void despawn();
}

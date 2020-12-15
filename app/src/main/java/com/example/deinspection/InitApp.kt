package com.example.deinspection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deinspection.activities.CarActivity
import com.example.deinspection.activities.MainMenuActivity
import com.example.deinspection.activities.NewCarActivity

import kotlinx.android.synthetic.main.activity_init_app.*
import com.example.deinspection.classes.*

const val CAR_NUMBER = "com.example.deinspection.CAR"

class MainActivity : AppCompatActivity() {
    companion object {
        val carList: MutableList<Car> = ArrayList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_app)

        // read from bin file
        // ver vars needed to global var
        //something like that

        //when reading is finished : or set an animation for the loading bar
        //activity main is the first page on the app, just the logo + loading bar(?)
        //
        show_btn.setOnClickListener {
            val intent = Intent(this, PopUpWindow::class.java)
            intent.putExtra("popuptitle", "Error")
            intent.putExtra("popuptext", "Sorry, that email address is already used!")
            intent.putExtra("popupbtn", "OK")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }



        loaded()
    }

    private fun loaded(){
        //go to MainMenu
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        
    }





}
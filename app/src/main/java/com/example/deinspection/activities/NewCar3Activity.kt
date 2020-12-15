package com.example.deinspection.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import com.example.deinspection.ATTRIBUTE
import com.example.deinspection.R
import com.example.deinspection.classes.Car
import kotlinx.android.synthetic.main.activity_new_car_2.*
import kotlinx.android.synthetic.main.activity_new_car_3.*

class NewCar3Activity : AppCompatActivity() {
    var car= Car()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_3)

        // if the car already exists, we fill it with the info we already have
        // FOR THE LOVE OF GOD IGNORE THIS SIMPLIFICATION
        // O CARRO NAO VAI SER SEMPRE VAZIO ELE APENAS ESTÁ VAZIO PORQUE ELE FOI DEFINIDO NA LINHA 19
        if (car != null) filledAlready()


        btnBackAL.setOnClickListener() {

            //pop up
            //do you want to leave without saving?
            val dialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle("DEInspection")
                    .setMessage("Pretende sair sem guardar?")
                    .setPositiveButton("Sair", null)
                    .setNegativeButton("Guardar e sair", null)
                    .setNeutralButton("Cancelar", null)
                    .show()
            val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton: Button = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            val neutralButton: Button = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)

            //leave without saving
            positiveButton.setOnClickListener() {
                dialog.dismiss()
                goBack()
            }
            // save and then leave
            negativeButton.setOnClickListener() {
                //save info
                saveInfo()
                Toast.makeText(this@NewCar3Activity, "Saving info", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                goBack()
            }
            //cancel and continue on this page
            neutralButton.setOnClickListener() {
                dialog.dismiss()
            }
        }


        btnNext3.setOnClickListener() {
            saveInfo()

            var number = 1

            val intent = Intent(this, CarActivity::class.java)
            intent.putExtra(ATTRIBUTE, number)
            //intent.putExtra(CAR_LIST, list)
            // send the list of all the cars
            startActivity(intent)
        }

    }


    fun filledAlready() {

        seekBar.progress= car.oil.reminder
        seekBar2.progress= car.inspection.reminder
        seekBar3.progress= car.stamp.reminder
        seekBar4.progress= car.tirePressure.reminder
        seekBar5.progress= car.tires.reminder
        seekBar6.progress = car.airFilters.reminder
        seekBar7.progress = car.windowCleaner.reminder
        seekBar8.progress = car.custom.reminder
        seekBar9.progress = car.custom2.reminder

        seekBar.progress= 10
        seekBar2.progress= 1
    }



    //save all the info in this page
    fun saveInfo(){
        car.oil.reminder= seekBar.progress
        car.inspection.reminder = seekBar2.progress
        car.stamp.reminder =  seekBar3.progress
        car.tirePressure.reminder= seekBar4.progress
        car.tires.reminder= seekBar5.progress
        car.airFilters.reminder = seekBar6.progress
        car.windowCleaner.reminder = seekBar7.progress
        car.custom.reminder= seekBar8.progress
        car.custom2.reminder = seekBar9.progress
    }


    //where the back button goes to
    fun goBack(){
        val intent = Intent(this, NewCar2Activity::class.java)
        startActivity(intent)
    }




    fun updateSeekBarTV() {

    }


    fun editTV(value: Int): String {
        val aux: Int
        var str: String =""
        //"REMINDE ME "
        when (value) {
            0 -> str ="never" //0
            1 -> str ="every day" //1
            in 2..6 -> str ="every @value days" //1-6days
            in 7..10 -> {
                aux = 7 * (value - 6) //1-4 weeks
                str = if (aux== 1) "every week"
                else "every @aux weeks"
            }
            //in 100..Int.MAX_REMINDER //1-12 months
            in 11..21 -> {
                aux = (value - 10) //1-11 months
                str = if (aux== 1) "every month"
                else "every @aux months"
            }
            in 22..26 -> {
                aux = (value - 21) // 1-5 years
                str = if (aux== 1) "every year"
                else "every @aux years"
            }
            else -> println("something went wrong value is out of bounds")
        }
        return str

    }


    fun seekBar(){




        // defining SeekBar
        val seek = SeekBar(this)
        val lParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lParams.setMargins(50, 50, 50, 50)
        seek.layoutParams = lParams

        val lLayout = findViewById<LinearLayout>(R.id.container)
        // Adding SeekBar to LinearLayout
        lLayout?.addView(seek)

        seek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    // write custom code when progress is changed
                    //listener for seekbar

                    //only for tests
                    val a = editTV(seekBar.progress)

                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // write custom code when touch is started.
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // write custom code when touch is stopped
                }
            })
    }

/*

    fun multipleSeekBar(){
        val seekbar1 = SeekBar(this)
        val seekbar2 = SeekBar(this)
        val seekbar3 = SeekBar(this)
        val seekbar4 = SeekBar(this)
        TextView result1;
        TextView result2
        TextView result3;
        int value;


            seekbar1 = (SeekBar)findViewById(R.id.seekBar1);
            seekbar2 = (SeekBar)findViewById(R.id.seekBar2);
            seekbar3 = (SeekBar)findViewById(R.id.seekBar3);
            result1 = (TextView)findViewById(R.id.textView6);
            result2 = (TextView)findViewById(R.id.textView7);
            result3 = (TextView)findViewById(R.id.textView8);


            //set change listener
            seekbar1.setOnSeekBarChangeListener(this);
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            value = progress;

            switch (seekBar.getId())
            {
                case R.id.seekBar1:
                result1.setText ("Value:"+value);
                break;
                case R.id.seekBar2:
                result2.setText ("Value:"+value);
                break;
                case R.id.seekBar3:
                result3.setText ("Value:"+value);
                break;
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    */



}


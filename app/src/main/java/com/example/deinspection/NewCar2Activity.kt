package com.example.deinspection

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi

class NewCar2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_car_3)
        //seekBar()

       //var car = Car()
        // idk how to do this uwu
    }

    //when btn finish is clicked
    @RequiresApi(Build.VERSION_CODES.O)
    fun finishButton(view: View){
    //save all seekbar info, dates etc
        //car.updateReminder("oil", seekbaroil.progress)
        //for all the attributes

        //we should get this from the array of cars
        //this is car number = number
        var number= 1

        val intent = Intent(this, CarActivity::class.java)
        intent.putExtra(CAR_NUMBER, number)
        //intent.putExtra(CAR_LIST, list)
        // send the list of all the cars
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


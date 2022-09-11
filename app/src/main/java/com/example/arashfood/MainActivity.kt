package com.example.arashfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arashfood.Room.FoodDao
import com.example.arashfood.Room.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_new_item.view.*
import kotlinx.android.synthetic.main.item_food.*


class MainActivity : AppCompatActivity()  {

     lateinit var foodlists : ArrayList<Food>
     lateinit var foodDao: FoodDao
     lateinit var myAdopter:FoodAdopter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*foodlists = arrayListOf<Food>(

            Food("Ghorme Sabzi","Norian","50000","Tehran" , 4,4f,"https://panamag.ir/wp-content/uploads/2021/08/ghorme-sabzi.jpg" ),
            Food("Mahi","Darya Food","62500","Mazandran" , 5,3.6f,"https://ashmazi.com/wp-content/uploads/2020/10/sabazi-polo-5.jpg" ),
            Food("Tahchin","Snap Food","47800","Tehran" , 3,4.3f,"https://zarinbano.com/wp-content/uploads/tahchin-morgh-2.jpg" ),
            Food("Morgh","Joshan Paz","30000","Amool" , 7,2.8f,"https://bia2safa.net/goto/file/image/2590975" ),
            Food("Kabab","Nayeb","80000","Tehran" , 1,5f,"https://panamag.ir/wp-content/uploads/2021/09/koubide-fer.jpg"),
            Food("Joje","Nayeb","75000","Tehran" , 2,4.5f,"https://img.tebyan.net/big/1396/08/112815013710238100112232904024924319915167.jpg?w=1200" ),
            Food("Koko","Mamany","25000","Shiraz" , 6,3f,"https://redmag.ir/wp-content/uploads/2022/07/red-mag-kookoo-sibzamini.jpg" ),

        )*/

        val foodDao=MyDatabase.getDatabase(this).foodDao


        myAdopter = FoodAdopter(foodlists,this)
        res.adapter = myAdopter
        res.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        addfood.setOnClickListener {

            val dialog = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            val view = layoutInflater.inflate(R.layout.dialog_add_new_item,null)
            dialog.setView(view)
            dialog.setCancelable(true)
            val show = dialog.show()

            view.btn_done.setOnClickListener {

                if(view.outlinedTextField.edt.text.toString().length < 2 || view.outlinedTextField2.edt2.text.toString().length < 2 || view.outlinedTextField3.edt3.text.toString().length < 2 || view.outlinedTextField4.edt4.text.toString().length < 2 || view.outlinedTextField5.edt5.text.toString().length < 2) {

                    Toast.makeText(this,"Wrong",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

              /*  myAdopter.Addfood(
                    Food(view.outlinedTextField.edt.text.toString(),
                        view.outlinedTextField2.edt2.text.toString(),
                        view.outlinedTextField3.edt3.text.toString(),
                        view.outlinedTextField4.edt4.text.toString(),
                        0,
                        0f,
                        view.outlinedTextField5.edt5.text.toString(),
                    ))*/

                show.dismiss()
            }
        }
    }

    private fun showAlldata(){
        val foodData=foodDao.getAllFood()

        myAdopter = FoodAdopter(ArrayList(foodData),this)
        res.adapter = myAdopter
        res.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

    }
}
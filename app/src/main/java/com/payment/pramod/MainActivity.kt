package com.payment.pramod

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.payment.pramod.databinding.ActivityMainBinding
import com.payment.pramod.utils.Config
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() , PaymentResultListener {


    private  var famount:kotlin.Int = 0
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.checkBox1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (binding.checkBox1.isChecked()) {
                val price: Int = binding.price1.getText().toString().toInt()
                famount = famount + price
                binding.totaltext.setText("Total :$famount")
            } else {
                val price: Int = binding.price1.getText().toString().toInt()
                famount = famount - price
                binding.totaltext.setText("Total :$famount")
            }
        })

        binding.checkBox2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if ( binding.checkBox2.isChecked) {
                val price: Int = binding.price2.getText().toString().toInt()
                famount = famount + price
                binding.totaltext.setText("Total :$famount")
            } else {
                val price: Int = binding.price2.getText().toString().toInt()
                famount = famount - price
                binding.totaltext.setText("Total :$famount")
            }
        })
        binding.checkBox3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{compoundButton,b ->
            if(binding.checkBox3.isChecked){

                val price:Int = binding.price3.text.toString().toInt()
                famount = famount +price
                binding.totaltext.setText("Total:$famount")

            }else{
                val price: Int = binding.price3.getText().toString().toInt()
                famount = famount - price
                binding.totaltext.setText("Total :$famount")
            }
        })


        binding.idBtnPay.setOnClickListener {
            paymentSendData()
        }

    }

    private fun paymentSendData() {

        val samount = famount.toString()
        val amount = Math.round(samount.toFloat() * 100)
        Log.e("paymentSendData: ",amount.toString() )
        val checkout = Checkout()
        checkout.setKeyID(Config.SecretKey);
        checkout.setImage(R.drawable.brand);
       val obj = JSONObject()
        try {


            `obj`.put("name", "PizzaHut")
            `obj`.put("description", "Pizza Order Payment")
            `obj`.put("theme.color", "")
            `obj`.put("amount", amount)
            `obj`.put("prefill.contact", "9910914510")
            `obj`.put("prefill.email", "pramodkumarp6@gmail.com")
            checkout.open(this@MainActivity, `obj`)

        }catch (e:JSONException){
            e.printStackTrace()
        }



    }

    override fun onPaymentSuccess(s: String?) {
        Toast.makeText(this@MainActivity, "Order Successfully. Transaction No :$s", Toast.LENGTH_SHORT).show()
       // fprice.setText("0.00");
        binding.checkBox1.setChecked(false);
        binding.checkBox2.setChecked(false);
        binding.checkBox2.setChecked(false);

    }

    override fun onPaymentError(p0: Int, p1: String?) {
        binding.checkBox1.setChecked(false);
        binding.checkBox2.setChecked(false);
        binding.checkBox2.setChecked(false);

    }
}
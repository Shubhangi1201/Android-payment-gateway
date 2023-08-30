package com.example.payment

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    lateinit var btn: Button
    lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.button)
        btn.setOnClickListener{
            makePayment()
            Toast.makeText(applicationContext,"Error in payment: ",Toast.LENGTH_LONG).show()

        }
    }

    private fun makePayment() {

            val activity: Activity = this
            val co = Checkout()

            try {
                val options = JSONObject()
                options.put("name","SSJ")
                options.put("description","Demoing Charges")
                //You can omit the image option to fetch the image from the dashboard
                options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
                options.put("theme.color", "#3399cc");
                options.put("currency","INR");

                options.put("amount","50000")//pass amount in currency subunits


                val prefill = JSONObject()
                prefill.put("email","")
                prefill.put("contact","")

                options.put("prefill",prefill)
                co.open(activity,options)
            }catch (e: Exception){
                Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(applicationContext,"pyament successful: "+ p0,Toast.LENGTH_LONG).show()
        txt.setText("Payment successful")

    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(applicationContext,"Error in payment: "+ p0,Toast.LENGTH_LONG).show()
        txt.setText("Payment failed")

    }
}
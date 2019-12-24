package com.example.konekdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_fakultas.*
//import kotlinx.android.synthetic.main.fakultas_list.cardview_list
import org.json.JSONObject

class ManageFakultasActivity : AppCompatActivity() {

    lateinit var i : Intent
    lateinit var add : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_fakultas)

        add = findViewById(R.id.btnCreate)

        i = intent

        if (i.hasExtra("editmode")){
            if (i.getStringExtra("editmode").equals("1")){
                onEditMode()
            }
        }
        add.setOnClickListener{
            onCreate()
        }
    }

    private fun onEditMode(){
        txt_kodefakultas.setText(i.getStringExtra("txt_kodefakultas"))
        txt_namafakultas.setText(i.getStringExtra("txt_namafakultas"))

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
    }

    private fun onCreate(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan Data ...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("Kode Fakultas",txt_kodefakultas.text.toString())
            .addBodyParameter("Nama Fakultas",txt_namafakultas.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()

                    Toast.makeText(applicationContext,response?.getString("message"),Toast.
                        LENGTH_SHORT).show()
                    if (response?.getString("message")?.contains("succesfully")!!){
                        this@ManageFakultasActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                }
            })
    }
}

package com.zuri.contact_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.zuri.contact_list.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private val adapter = ContactAdapter()
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData(binding)
    }

    private fun setupData(binding: ActivityContactBinding){
        title = intent?.getStringExtra("Title")
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.add_contact_dialog,null)

        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameEt)
        val no = view.findViewById<TextView>(R.id.numberEt)
        val save = view.findViewById<Button>(R.id.saveBt)

        no.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                save.isEnabled = s?.length!! >= 11
            }

        })

        val alertDialog = builder.create()

        save.setOnClickListener {
            val contact = Contact(name.text.toString(),no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)

            alertDialog.dismiss()
        }

        binding.fab.setOnClickListener{
            alertDialog.show()
        }
    }
}
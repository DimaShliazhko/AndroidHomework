package com.dshliazhko.android.homeworkthree

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class AddContactActivity : AppCompatActivity(), Serializable {
    private var save: ImageButton? = null
    private var editNameContact: EditText? = null
    private var editContact: EditText? = null
    private val editNameContact_: String? = null
    private val editContact_: String? = null
    private var radioGroup: RadioGroup? = null
    private val imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val contact: Contact
        contact = Contact()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)
        save = findViewById(R.id.save)
        editNameContact = findViewById(R.id.edit_name)
        editContact = findViewById(R.id.edit_contact)
        contact.image_View = R.drawable.ic_baseline_contact_phone_24

        //Создаем данные для передачи:

        save.setOnClickListener(View.OnClickListener {
            contact.edit_name = editNameContact.getText().toString()
            contact.edit_contact = editContact.getText().toString()
            Store.getStore().add(contact)
            finish()
        })
        radioGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
        radioGroup!!.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radioButton1 -> {
                    editContact.setHint("Contact")
                    contact.image_View = R.drawable.ic_baseline_contact_phone_24
                }
                R.id.radioButton2 -> {
                    editContact.setHint("Email")
                    contact.image_View = R.drawable.ic_baseline_contact_mail_24
                }
            }
        }
    }

    companion object {
        private const val id = 0
    }
}
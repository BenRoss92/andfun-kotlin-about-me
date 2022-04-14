/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Main Activity of the AboutMe app. This app demonstrates:
 *     * LinearLayout with TextViews, ImageView, Button, EditText, and ScrollView
 *     * ScrollView to display scrollable text
 *     * Getting user input with an EditText.
 *     * Click handler for a Button to retrieve text from an EditText and set it in a TextView.
 *     * Setting the visibility status of a view.
 *     * Data binding between MainActivity and activity_main.xml. How to remove findViewById,
 *       and how to display data in views using the data binding object.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName = MyName("Ben Ross")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        // Add a click handler to the Done button that displays the inputted text in the TextView and hides the EditText and button.

        // Get a reference to the done button

        // Add a click handler to the done button

        // When 'done' button is clicked, we should:
            // get the value that the user has inputted into the nickname_edit editable text field
            // set the value of the nickname result field (with ID nickname_text) to be the value entered into the editable text field
            // hide the done_button - visibility = gone
            // hide the nickname_edit field - visibility = gone
            // show the result field by changing the visibility of the nickname_text field to 'visible'

        // Using findViewById (older approach than using Kotlin extensions plugin):
        // findViewById<Button>(R.id.done_button).setOnClickListener {  }

        // Using Kotlin synthetics via the Kotlin extensions plugin (instead of having to use older findViewById approach) - avoid as will soon be deprecated
        // + doesn't provide type safety at compile time:
//        done_button.setOnClickListener {
//            addNickname(it)
//        }

        // Using Data Binding (instead of Kotlin synthetics or findViewById) as this is the most modern and best approach as it provides
        // type safety at compile time:
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
        // Using the 'apply' block is a shortcut for calling binding.X on every line,
        // e.g. binding.nicknameText.text becomes nicknameText.text, and so on for every line
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()


            // Refresh the UI with the new data by invalidating all existing binding expressions to make these expressions get created again with the new data
            // - we did this because in the line above we updated the data contained within the binding object - i.e. we updated the 'text' property inside of binding.nicknameText
            invalidateAll()

            doneButton.visibility = View.GONE
            nicknameEdit.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // After the 'done' button is clicked, hide the keyboard:
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
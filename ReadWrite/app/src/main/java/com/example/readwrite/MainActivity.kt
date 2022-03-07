package com.example.readwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.readwrite.databinding.ActivityMainBinding
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        file = File(filesDir, "todoFile.txt")
    }

    fun displayList(view: View) {
        try {
            val reader = Scanner(file)
            val builder = StringBuilder()

            while (reader.hasNextLine()) {
                builder
                    .append(reader.nextLine())
                    .append('\n')
            }
            binding.displayText.text = builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun addTask(view: View) {
        val task = binding.txtTaskInput.text.toString()
        val toast = try {
            val stream = FileWriter(file, true)
            stream.append("- $task\n")

            Toast.makeText(this, "$task is added successfully", Toast.LENGTH_SHORT)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG)
        }

        toast.show()
    }



}
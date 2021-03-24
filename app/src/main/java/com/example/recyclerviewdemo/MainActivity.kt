package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var studentList = ArrayList<Student>()
    lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Main).launch {
            generateData()
        }

        recycler = findViewById(R.id.studentrecyclerview)
        recycler.adapter = MyAdapter(studentList)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        val btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val name : String = findViewById<EditText>(R.id.tfName).text.toString()
            val prog : String = findViewById<EditText>(R.id.tfProgramme).text.toString()

            val newRec = Student (null,0, name, prog)

            CoroutineScope(IO).launch {
                val studentDAO = StudentDB.getDatabase(application).studentDAO()
                studentDAO.addStudent(newRec)
            }
        }

        val btnView: Button = findViewById(R.id.btnView)

        btnView.setOnClickListener {
            CoroutineScope(Main).launch {
                val studentDAO = StudentDB.getDatabase(application).studentDAO()
                val studentList: ArrayList<Student> = studentDAO.getAllStudent().toCollection(ArrayList())

                if(studentList.isNotEmpty()) {
                    recycler.adapter = MyAdapter(studentList)
                }
            }
        }
    }

    private suspend fun generateData() {
        val studentDAO = StudentDB.getDatabase(application).studentDAO()

        studentDAO.addStudent(Student(null, R.drawable.ic_baseline_tag_faces_24, "John", "RSF"))
        studentDAO.addStudent(Student(null, R.drawable.ic_baseline_tag_faces_24, "Johny", "RSD"))
        studentDAO.addStudent(Student(null,R.drawable.ic_baseline_tag_faces_24, "Nhoj", "RST"))
        studentDAO.addStudent(Student(null, R.drawable.ic_baseline_tag_faces_24, "Ynhoj", "RDS"))
    }
}
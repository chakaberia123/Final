package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Todo
import com.example.myapplication.TodoAdapter
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var btnAddTodo: Button
    private lateinit var etTodoTitle: EditText
    private lateinit var btnDeleteDoneTodos: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoAdapter = TodoAdapter(mutableListOf())

        val database= FirebaseDatabase.getInstance().getReference("recyclerView")

        init()


        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(activity)



        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            val todo = Todo(todoTitle)
            todoAdapter.addTodo(todo)
            etTodoTitle.text.clear()

            database.child(todo.toString()).setValue(todo)





        }


        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }

    private fun init() {
        rvTodoItems = requireView().findViewById(R.id.rvTodoItems)
        btnAddTodo = requireView().findViewById(R.id.btnAddTodo)
        btnDeleteDoneTodos = requireView().findViewById(R.id.btnDeleteDoneTodos)
        etTodoTitle = requireView().findViewById(R.id.etTodoTitle)


    }



}
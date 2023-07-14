package com.example.noteappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public List<ListItems> itemList2;
    private ItemAdapter itemAdapter;

    private int taskCount = 0;

    private TextView taskCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al TextView del contador de tareas
        taskCountTextView = findViewById(R.id.taskCountTextView);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList2 = new ArrayList<>();

        itemAdapter = new ItemAdapter(itemList2);
        recyclerView.setAdapter(itemAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500); //es la duración de la animación de agregado en milisegundos
        itemAnimator.setRemoveDuration(500); //es la duración de la anmiación de eliminación en mimls
        itemAnimator.setChangeDuration(500); //
        itemAnimator.setMoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);

        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });
    }

    //private  void addItem(){
    //    ListItems newItem = new ListItems("Nuevo Elemento", "Descripción Nuevo Elemento");
    //    itemList2.add(newItem);
    //    itemAdapter.notifyItemInserted(itemList2.size()-1);
    //}

    private void addItem() {
        // Inflar el diseño del diálogo personalizado
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);

        // Obtener referencias a los elementos de la vista del diálogo
        EditText titleEditText = dialogView.findViewById(R.id.editTextTitle);
        EditText descriptionEditText = dialogView.findViewById(R.id.editTextDescription);
        CheckBox importantCheckBox = dialogView.findViewById(R.id.checkBoxImportant);
        CheckBox startedCheckBox = dialogView.findViewById(R.id.checkBoxStarted);
        CheckBox finishedCheckBox = dialogView.findViewById(R.id.checkBoxFinished);

        // Crear el diálogo de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Agregar elemento");
        builder.setView(dialogView);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener los valores ingresados por el usuario
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                boolean isImportant = importantCheckBox.isChecked();
                boolean isStarted = startedCheckBox.isChecked();
                boolean isFinished = finishedCheckBox.isChecked();

                // Crear el nuevo elemento
                ListItems newItem = new ListItems(title, description, isImportant, isStarted, isFinished);
                itemList2.add(newItem);
                itemAdapter.notifyDataSetChanged();

                // Incrementar el contador de tareas
                taskCount++;
                updateTaskCount();
            }

        });
        builder.setNegativeButton("Cancelar", null);

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();


    }


    private void removeItem(){
        if (!itemList2.isEmpty())
        {
            int lastIndex = itemList2.size()-1;
            itemList2.remove(lastIndex);
            itemAdapter.notifyItemRemoved(lastIndex);
            // Decrementar el contador de tareas
            taskCount--;
            updateTaskCount();
        }
    }

    private void updateTaskCount() {
        // Actualizar el texto del TextView del contador de tareas
        taskCountTextView.setText("Número de tareas: " + taskCount);
    }


}
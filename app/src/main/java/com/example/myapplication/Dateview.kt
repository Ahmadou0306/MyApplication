//package com.example.myapplication
//
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import com.google.android.material.datepicker.MaterialDatePicker
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.TimeUnit
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
//
////@Preview(showBackground = true)
//@Composable
//fun DateCalculator() {
//    var startDate by remember { mutableStateOf("") }
//    var endDate by remember { mutableStateOf("") }
//    var interval by remember { mutableStateOf("") }
//
//    val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//
//    val startPicker = remember { MaterialDatePicker.Builder.datePicker().build() }
//    val endPicker = remember { MaterialDatePicker.Builder.datePicker().build() }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(
//            value = startDate,
//            onValueChange = { startDate = it },
//            label = { Text("Start date") },
//            readOnly = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = endDate,
//            onValueChange = { endDate = it },
//            label = { Text("End date") },
//            readOnly = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { startPicker.show((context as AppCompatActivity).supportFragmentManager, "start_picker") },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Select start date")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { endPicker.show((context as AppCompatActivity).supportFragmentManager, "end_picker") },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Select end date")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
//                    val start = dateFormatter.parse(startDate)!!.time
//                    val end = dateFormatter.parse(endDate)!!.time
//                    val diff = end - start
//                    val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
//                    interval = "$days days"
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Calculate interval")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = interval,
//            style = MaterialTheme.typography.h5,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//    }
//
//    startPicker.addOnPositiveButtonClickListener { date ->
//        startDate = dateFormatter.format(Date(date))
//    }
//
//    endPicker.addOnPositiveButtonClickListener { date ->
//        endDate = dateFormatter.format(Date(date))
//    }
//}

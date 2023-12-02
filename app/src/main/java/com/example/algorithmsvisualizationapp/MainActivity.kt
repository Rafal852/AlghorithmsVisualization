package com.example.algorithmsvisualizationapp

import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algorithmsvisualizationapp.domain.presentation.SortViewModel
import com.example.algorithmsvisualizationapp.ui.theme.AlgorithmsVisualizationAppTheme
import com.example.algorithmsvisualizationapp.ui.theme.gray

class MainActivity : ComponentActivity() {

    private val sortViewModel = SortViewModel()
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgorithmsVisualizationAppTheme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gray)
                        .padding(20.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Button(onClick = {
                        sortViewModel.startSorting()
                    }
                    ) {
                        Text(
                            "Sort List",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    }
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ){
                        items(
                            sortViewModel.listToSort,
                            key = {
                                it.id
                            }
                        ){
                            val borderStroke = if(it.isCurrentlyCompared){
                                BorderStroke(width = 3.dp,Color.White,)
                            }else{
                                BorderStroke(width = 0.dp,Color.Transparent)
                            }
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(it.color, RoundedCornerShape(15.dp))
                                    .border(borderStroke, RoundedCornerShape(15.dp))
                                    .animateItemPlacement(
                                        tween(300)
                                    ),
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    "${it.value}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
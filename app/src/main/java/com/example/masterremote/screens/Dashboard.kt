package com.example.masterremote.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.masterremote.data.Feature
import com.example.masterremote.data.defaultUser
import com.example.masterremote.data.features

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(onExitClicked: () -> Unit) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ), title = { Text(text = "Olá, ${defaultUser.username}") }, actions = {
            ExitButton(onExitClicked = onExitClicked)
        })
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.size(50.dp))
            QuickInfoCard()
        }
    }
}


@Composable
fun QuickInfoCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(200.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Resumo", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconBox(imageVector = Icons.Outlined.Person, category = "Usuários", quant = "100")
                IconBox(imageVector = Icons.Outlined.Warning, category = "Avisos", quant = "2")
                IconBox(imageVector = Icons.Outlined.Error, category = "Erros", quant = "0")
            }
            ServerStatusLight(isServerOnline = true)
        }
    }
    Spacer(modifier = Modifier.size(20.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(features) { _, feature ->
            FeatureBox(feature = feature)
        }
    }
}

@Composable
fun ServerStatusLight(isServerOnline: Boolean) {
    val serverStatusColor = if (isServerOnline) Color.Green else Color.Red
    val contentDescription = if (isServerOnline) "Online" else "Offline"
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Estado do servidor")
        Icon(
            imageVector = Icons.Outlined.Dns,
            contentDescription = contentDescription,
            tint = serverStatusColor
        )
    }
}

@Composable
fun ExitButton(onExitClicked: () -> Unit) {
    IconButton(onClick = onExitClicked) {
        Icon(
            imageVector = Icons.Outlined.ExitToApp, contentDescription = "Exit"
        )
    }
}

@Composable
fun IconBox(imageVector: ImageVector, category: String, quant: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = category,
            modifier = Modifier.size(40.dp)
        )
        Text(text = category, style = TextStyle(fontSize = 16.sp), fontWeight = FontWeight.Thin)
        Text(text = quant, style = TextStyle(fontSize = 18.sp))
    }
}

@Composable
fun FeatureBox(feature: Feature) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .clickable { },
            painter = painterResource(id = feature.icon),
            contentScale = ContentScale.FillBounds,
            contentDescription = feature.title
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = feature.title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium), textAlign = TextAlign.Center
        )
    }
}
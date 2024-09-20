package com.mouredev.pokemonjetpackcompose.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.mouredev.pokemonjetpackcompose.ui.theme.PokemonJetpackComposeTheme

/**
 * Created by MoureDev by Brais Moure on 28/10/22.
 * www.mouredev.com
 */

class PokemonListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PokemonList()
                }
            }
        }
    }
}

@Composable
fun PokemonList() {

    val viewModel = PokemonListViewModel()
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text("Pokémon list")
                    }
                )
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    placeholder = {
                        Text(text = "Search Pokémon...")
                    },
                    modifier = Modifier
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        ) {
            val filterPokemon = viewModel.pokemonList.filter {
                it.name.contains(text.trim(), ignoreCase = true)
            }
            items(filterPokemon) { pokemon ->
                PokemonCell(pokemon = pokemon)
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun PokemonListDefaultPreview() {
    PokemonJetpackComposeTheme {
        PokemonList()
    }
}
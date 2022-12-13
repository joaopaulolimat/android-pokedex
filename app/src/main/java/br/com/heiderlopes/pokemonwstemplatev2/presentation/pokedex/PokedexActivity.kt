package br.com.heiderlopes.pokemonwstemplatev2.presentation.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.heiderlopes.pokemonwstemplatev2.R
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityPokedexBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PokedexActivity : AppCompatActivity() {
    private val pokedexViewModel: PokedexViewModel by viewModel()
    val picasso: Picasso by inject()
    private lateinit var pokemon: Pokemon

    private val viewBinding by lazy {
        ActivityPokedexBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        val pokemonNumber = intent.getStringExtra("POKEMON") ?: ""
        pokedexViewModel.getPokemon(pokemonNumber)

        registerObserver()
    }

    fun registerObserver() {
        pokedexViewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    setValues(it.data)
                }
                is ViewState.Loading -> {
                }
                is ViewState.Failure -> {
                    Toast.makeText(
                        this, it.throwable.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    private fun setValues(pokemon: Pokemon) {
        this.pokemon = pokemon
        viewBinding.tvPokemonName.text = "${pokemon.number} ${pokemon.name}"
        picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}").placeholder(R.drawable.logo_pokemon).into(viewBinding.
        ivPokemon)
    }
}
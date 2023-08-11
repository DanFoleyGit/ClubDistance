package com.multiplatform.clubdistances.homeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.multiplatform.clubdistances.ClubsApplication
import com.multiplatform.clubdistances.homeScreen.presentation.HomeScreenViewModel
import com.multiplatform.clubdistances.homeScreen.presentation.HomeScreenViewModelFactory

class HomeScreenComposeFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModels() {
        HomeScreenViewModelFactory((activity?.application as ClubsApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    // In Compose world
                    Row (horizontalArrangement = Center){
                        Text (
                            text = "Hello Compose!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allClubs.observe(viewLifecycleOwner, Observer { clubs ->
            clubs?.let{
//                setContent{

//                }
            }
        })
    }
}

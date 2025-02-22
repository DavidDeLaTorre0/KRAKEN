import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kraken.ui.theme.Fondo
import com.example.kraken.ui.theme.FondoTopBar
import com.example.kraken.ui.theme.Texto
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun HomeScreen(db: FirebaseFirestore) {
    fun handleLogout() {
        // Aquí puedes agregar la lógica de cerrar sesión
        println("Cerrando sesión...")
    }

    Scaffold(
        topBar = { TopBar(onLogoutClick = { handleLogout() }) }
    ) { paddingValues ->
        Content(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onLogoutClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Hola, Username", color = Texto)},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoTopBar),
        actions = {
            IconButton(onClick = onLogoutClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Cerrar sesión",
                    tint = Texto
                )
            }
        }
    )
}


@Composable
fun Content(paddingValues: PaddingValues) {
    LazyColumn (modifier = Modifier.padding(paddingValues).fillMaxSize().background(Fondo)) {

    }
}
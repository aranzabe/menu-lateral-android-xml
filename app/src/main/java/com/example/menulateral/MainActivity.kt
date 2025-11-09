package com.example.menulateral

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.menulateral.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Configura el Toolbar
        setSupportActionBar(binding.toolbar)

        //Inicializa DrawerLayout
        val drawerLayout: DrawerLayout = binding.drawerLayout

        //Habilita el botón de hamburguesa para abrir el drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        //******************* Para iniciar el navController que me permitirá navegar de un fragmento a otro.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        //En el archivo mi_nav.xml los id de los fragmentos en el nav_graph debe coincidir con los id de los elementos en mi_menu.xml.
        //Es decir para que funcione esto de arriba los fragmentos se llamarán:
        // -- en mi_nav: navigation_fragmento_a
        // -- en menu: navigation_fragmento_a


        //Define los destinos principales para que siempre muestre el ícono de hamburguesa en lugar de la flecha de retroceso
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_opcion1, R.id.navigation_opcion2, R.id.navigation_opcion3),
            drawerLayout
        )
        //appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout) //--> De esta manera solo se mostraría el ícono de hamburguesa en el Fragmento 1 (principal) y retroceso en el resto.

        //Configura AppBarConfiguration para sincronizar el botón de hamburguesa.
        setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.title = "Opción 1"

        //Configura NavigationView
        val navView: NavigationView = binding.navigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            //Maneja las selecciones del menú aquí
            when (menuItem.itemId) {
                R.id.navigation_opcion1 -> {
                    navController.navigate(R.id.navigation_opcion1)
                    supportActionBar?.title = "Opción 1"
//                    supportActionBar?.hide()
//                    Toast.makeText(this, "Opción 1", Toast.LENGTH_LONG).show()
                }
                R.id.navigation_opcion2 -> {
                    navController.navigate(R.id.navigation_opcion2)
                    supportActionBar?.title = "Opción 2"
//                    Toast.makeText(this, "Opción 2", Toast.LENGTH_LONG).show()
                }
                R.id.navigation_opcion3 -> {
                    navController.navigate(R.id.navigation_opcion3)
                    supportActionBar?.title = "Opción 3"
//                    Toast.makeText(this, "Opción 3", Toast.LENGTH_LONG).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START) //Cierra el drawer después de seleccionar una opción
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //Método para abrir el drawer al presionar el ícono de menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val drawerLayout: DrawerLayout = binding.drawerLayout
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
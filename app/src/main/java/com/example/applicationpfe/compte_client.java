package com.example.applicationpfe;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.applicationpfe.databinding.CompteClientBinding;
import com.example.applicationpfe.ui.demandes.DemandeFragment;
import com.example.applicationpfe.ui.history.HistoryFragment;
import com.example.applicationpfe.ui.home.HomeFragment;
import com.example.applicationpfe.ui.logout.LogoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class compte_client extends AppCompatActivity implements LocationListener {

    private CompteClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CompteClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.person:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.demandes:
                    replaceFragment(new demande_frag());
                    break;
                case R.id.notification:
                    replaceFragment(new history_frag());
                    break;
                case R.id.library:
                    replaceFragment(new deconexion_frag());
                    break;
            }
            return true;
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.person:
                        // Naviguer vers l'écran d'accueil
                        navigateToHome();
                        return true;
                    case R.id.library:
                        // Afficher une boîte de dialogue pour confirmer la déconnexion
                        showLogoutConfirmationDialog();
                        return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onLocationChanged(Location location) {
        // Traitement de la nouvelle position
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Statut du fournisseur de localisation a changé
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Fournisseur de localisation activé
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Fournisseur de localisation désactivé
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(compte_client.this);
        builder.setTitle("Déconnexion");
        builder.setMessage("Êtes-vous sûr de vouloir vous déconnecter ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Effectuez les opérations de déconnexion ici

                // Créez une intention pour revenir à l'écran d'authentification
                Intent intent = new Intent(compte_client.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // L'utilisateur a choisi de rester connecté, ne rien faire
            }
        });
        builder.show();
    }

    private void navigateToHome() {
        // Naviguer vers l'écran d'accueil
    }
}
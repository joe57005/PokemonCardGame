package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ButtonControl bc=new ButtonControl();

    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= (ImageButton) findViewById(R.id.buttonCoinFlip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinFlipButton();
            }
        });
    }
        public void coinFlipButton(){
            Toast.makeText(getApplicationContext(), "Pressed.", Toast.LENGTH_LONG).show();
            bc.coinFlip();
        }








}
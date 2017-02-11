package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testing testing
        Button coinFlip= (Button) findViewById(R.id.coin);
        coinFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice= (int) (Math.random()*2);
                if(choice==0){
                    //Heads!
                }
                else{
                    //Tails!
                }
            }
        });






    }
}
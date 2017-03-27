package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ButtonControl bc=new ButtonControl();

    ImageButton imageButton;
    Button endStuff;
    Button damage;
    TextView damageText;

    Button switchy;
    boolean switchOn;

    public static final String MAIN_CARD="cardy";

    Card card;

    public static final Card[] cardArray= new Card[6];
    Card tempCard;
    //For swtiching cards.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainCardBtn;

        Card temp= (Card) getIntent().getSerializableExtra(MAIN_CARD);
        if(temp!=null) {
            cardArray[5]=temp;
        }
        else{
            cardArray[5]= new Card("First Pokemon");
        }

        for(int i=0; i<(cardArray.length-1); i++){
            cardArray[i]=new Card("Pokemon " + 1);
        }



        imageButton= (ImageButton) findViewById(R.id.buttonCoinFlip);
        endStuff= (Button) findViewById(R.id.endButton);
        mainCardBtn= (Button) findViewById(R.id.cardMain);
        damage= (Button) findViewById(R.id.damageBtn);
        damageText= (TextView) findViewById(R.id.damageText);
        switchy= (Button) findViewById(R.id.nintendoSwitch);
        switchy.setBackgroundColor(Color.GRAY);


        updateText();

        endStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dangy= cardArray[5].doTurn();
                if(dangy==0){
                    Toast.makeText(getApplicationContext(), cardArray[5].getName() + " sustained no points of damage!", Toast.LENGTH_LONG).show();
                    updateText();
                }
                else {
                    Toast.makeText(getApplicationContext(), cardArray[5].getName() + " sustained " + dangy + " points of damage!", Toast.LENGTH_LONG).show();
                    updateText();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinFlipButton();
            }
        });

        damage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardArray[5].setDamage(cardArray[5].getDamage()+10);
                updateText();
            }
        });

        mainCardBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i= new Intent(MainActivity.this, CardConfig.class);
                card= cardArray[5];
                i.putExtra(CardConfig.ASDF, card);
                startActivity(i);
            }
        });
    }
        public void coinFlipButton(){
            int choice= (int) (Math.random()*2);
            if(choice==0){
                Toast.makeText(getApplicationContext(), "Heads!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Tails!", Toast.LENGTH_LONG).show();
            }
        }

        public void switchListener(View v){
            switchOn=!switchOn;

            if(switchOn){
                switchy.setBackgroundColor(Color.BLUE);
            }
            else {
                switchy.setBackgroundColor(Color.GRAY);
            }
        }

        public void thisAppliesToAllCardsAndStuff(View v){
            if(switchOn) {
                int tempy = 0;

                if (v == findViewById(R.id.card1)) {
                    tempy = 0;
                }
                if (v == findViewById(R.id.card2)) {
                    tempy = 1;
                }
                if (v == findViewById(R.id.card3)) {
                    tempy = 2;
                }
                if (v == findViewById(R.id.card4)) {
                    tempy = 3;
                }
                if (v == findViewById(R.id.card5)) {
                    tempy = 4;
                }

                joesSwitchingMethod(tempy);
            }
            else{
                //TODO Something else;
            }
        }

        public void joesSwitchingMethod(int card){
            tempCard= cardArray[card];
            cardArray[card]=cardArray[5];
            cardArray[5]=tempCard;
            updateText();
        }

        public void updateText(){
            damageText.setText(cardArray[5].getName() + " damage: " + cardArray[5].getDamage());
        }


//Test change






}
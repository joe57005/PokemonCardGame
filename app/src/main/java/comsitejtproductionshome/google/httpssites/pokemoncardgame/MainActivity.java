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


/*
Still to do:
Animations for status effects
Make it so the switch button also removes statuses on the current card.
Add functionality to the "add" button

Darude - Sandstorm
 */


public class MainActivity extends AppCompatActivity {
    static int forChangingCardInfo;

    ImageButton imageButton;
    Button endStuff;
    Button damage;
    TextView damageText;

    Button switchy;
    boolean switchOn;

    Button removey;
    boolean removeOn;

    public static final String MAIN_CARD="cardy";

    Card card;

    public static final Card[] cardArray= new Card[6];
    Card tempCard;
    //For swtiching cards.
    Button[] cards= new Button[5];
    Button mainCardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Card temp= (Card) getIntent().getSerializableExtra(MAIN_CARD);

        if(temp!=null) {
            cardArray[forChangingCardInfo]=temp;
        }
        else {
            cardArray[5] = new Card("First Pokemon");
//            for (int i = 0; i < (cardArray.length - 1); i++) {
//                cardArray[i] = new Card("Pk" + (i + 1));
//            }
        }

        cards[0]= (Button) findViewById(R.id.card1);
        cards[1]= (Button) findViewById(R.id.card2);
        cards[2]= (Button) findViewById(R.id.card3);
        cards[3]= (Button) findViewById(R.id.card4);
        cards[4]= (Button) findViewById(R.id.card5);


        imageButton= (ImageButton) findViewById(R.id.buttonCoinFlip);
        endStuff= (Button) findViewById(R.id.endButton);
        mainCardBtn= (Button) findViewById(R.id.cardMain);
        damage= (Button) findViewById(R.id.damageBtn);
        damageText= (TextView) findViewById(R.id.damageText);
        switchy= (Button) findViewById(R.id.nintendoSwitch);
        removey= (Button) findViewById(R.id.removeBtn);


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
            if(removeOn){
                removeOn=!removeOn;
            }

            switchOn=!switchOn;

            if(switchOn){
                switchy.setBackgroundColor(Color.BLUE);
            }
            else {
                switchy.setBackgroundColor(Color.LTGRAY);
            }
        }


        public void removeListener(View v){
            if(switchOn){
                switchOn=!switchOn;
            }

            removeOn=!removeOn;

            if(removeOn){
                removey.setBackgroundColor(Color.RED);
            }
            else {
                removey.setBackgroundColor(Color.LTGRAY);
            }

        }

        public void thisAppliesToAllCardsAndStuff(View v){
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
            if (v == findViewById(R.id.cardMain)){
                tempy = 5;
            }

            //Log.i("hello", tempy + "");

            if(switchOn) {
                joesSwitchingMethod(tempy);
                switchOn=!switchOn;
                switchy.setBackgroundColor(Color.LTGRAY);
            }
            else if(removeOn){
                removingMethod(tempy);
                removeOn=!removeOn;
                removey.setBackgroundColor(Color.LTGRAY);
            }
            else{
                forChangingCardInfo=tempy;
                Intent i= new Intent(MainActivity.this, CardConfig.class);
                card= cardArray[tempy];
                i.putExtra(CardConfig.ASDF, card);
                startActivity(i);
            }
            updateText();
        }

        public void joesSwitchingMethod(int card){
            tempCard= cardArray[card];
            cardArray[card]=cardArray[5];
            cardArray[5]=tempCard;
        }

        public void removingMethod(int card){
            cardArray[card]=null;
        }

        public void addingMethod(View v){
            boolean isFull=true;

            for(int i=0; i<cardArray.length; i++){
                if(cardArray[i]==null){
                    cardArray[i]=new Card("Pkmn"+(i+1));
                    i=cardArray.length;
                    isFull=false;
                }
            }

            if(isFull){
                Toast.makeText(getApplicationContext(), "The bench is full!", Toast.LENGTH_LONG).show();
            }

            updateText();
        }

        public void updateText(){
            for(int i=0; i<cardArray.length; i++){
                if(cardArray[i]==null){
                    //cards[i].setText("No Pokemon");
                    cards[i].setBackgroundColor(Color.BLACK);
                    cards[i].setEnabled(false);
                }
                else{
                    if(i!=5) {
                        cards[i].setText(cardArray[i].getNickName());
                        cards[i].setBackgroundColor(Color.LTGRAY);
                        cards[i].setEnabled(true);
                    }
                    else{
                        mainCardBtn.setText(cardArray[i].getNickName());
                    }
                }
            }
            damageText.setText(cardArray[5].getName() + " damage: " + cardArray[5].getDamage());
        }


}
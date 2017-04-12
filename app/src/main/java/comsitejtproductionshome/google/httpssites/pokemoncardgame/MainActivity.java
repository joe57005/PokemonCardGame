package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    ButtonControl bc=new ButtonControl();

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
    //For swtiching cardBtns.
    Button[] cardBtns = new Button[5];
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
            for (int i = 0; i < (cardArray.length - 1); i++) {
                cardArray[i] = new Card("Pk" + (i + 1));
            }
        }

        cardBtns[0]= (Button) findViewById(R.id.card1);
        cardBtns[1]= (Button) findViewById(R.id.card2);
        cardBtns[2]= (Button) findViewById(R.id.card3);
        cardBtns[3]= (Button) findViewById(R.id.card4);
        cardBtns[4]= (Button) findViewById(R.id.card5);





        imageButton= (ImageButton) findViewById(R.id.buttonCoinFlip);
        endStuff= (Button) findViewById(R.id.endButton);
        mainCardBtn= (Button) findViewById(R.id.cardMain);
        damage= (Button) findViewById(R.id.damageBtn);
        damageText= (TextView) findViewById(R.id.damageText);
        switchy= (Button) findViewById(R.id.nintendoSwitch);
        removey= (Button) findViewById(R.id.btnRemoveCard);

        initButtonGraphics();


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

    public void initButtonGraphics(){
        //make all buttons the same size as a pokemon card
        int widthCardBtns=(int)((getResources().getDisplayMetrics().widthPixels)/5.5);
        int heightCardBtns=(int)(widthCardBtns*1.35);
        ViewGroup.LayoutParams cardLP= (ViewGroup.LayoutParams)cardBtns[1].getLayoutParams();
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(widthCardBtns,heightCardBtns );
        mainCardBtn.setLayoutParams(cardParams);
        mainCardBtn.setBackgroundResource(R.drawable.blankcard);
//        mainCardBtn.setGravity(Gravity.CENTER_HORIZONTAL);
        for(int i=0;i<cardBtns.length;i++){
            cardBtns[i].setLayoutParams(cardParams);
            cardBtns[i].setBackgroundResource(R.drawable.blankcard);

        }
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

        public void updateText(){
            for(int i=0; i<cardArray.length; i++){
                if(cardArray[i]==null){
                    cardBtns[i].setText("No Pokemon");
                }
                else{
                    if(i!=5) {
                        cardBtns[i].setText(cardArray[i].getNickName());
                    }
                    else{
                        mainCardBtn.setText(cardArray[i].getNickName());
                    }
                }
            }
            damageText.setText(cardArray[5].getName() + " damage: " + cardArray[5].getDamage());
        }


}
package comsitejtproductionshome.google.httpssites.pokemoncardgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class CardConfig extends AppCompatActivity {

    public static final String ASDF= "O Hai";

    Card card;

    CheckBox burned;
    CheckBox poisoned;
    CheckBox asleep;
    CheckBox paralyzed;
    CheckBox confused;

    TextView curText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_config);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        card= (Card) getIntent().getSerializableExtra(ASDF);

        burned = (CheckBox) findViewById(R.id.checkBurned);
        poisoned = (CheckBox) findViewById(R.id.checkPoisoned);
        asleep = (CheckBox) findViewById(R.id.checkAsleep);
        paralyzed = (CheckBox) findViewById(R.id.checkParalyzed);
        confused = (CheckBox) findViewById(R.id.checkConfused);
        Button evolve = (Button) findViewById(R.id.evolveBtn);

        Button done= (Button) findViewById(R.id.doneBtn);

        curText= (EditText) findViewById(R.id.curCardText);

        curText.setText(card.getName());


        burned.setChecked(false);
        poisoned.setChecked(false);
        asleep.setChecked(false);
        paralyzed.setChecked(false);
        confused.setChecked(false);


        //Making sure the checkboxes are set correctly
        burned.setChecked(card.isBurned());
        poisoned.setChecked(card.isPoisoned());
        asleep.setChecked(card.isAsleep());
        paralyzed.setChecked(card.isParalyzed());
        confused.setChecked(card.isConfused());



        //Listeners for when the state is checked.
        burned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                card.setBurned(burned.isChecked());
            }
        });
        poisoned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                card.setPoisoned(poisoned.isChecked());
            }
        });
        asleep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                card.setAsleep(asleep.isChecked());
            }
        });
        paralyzed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                card.setParalyzed(paralyzed.isChecked());
            }
        });
        confused.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                card.setConfused(confused.isChecked());
            }
        });




        evolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CardConfig.this, MainActivity.class);
                card.clearConditions();
                card.setName(curText.getText().toString());
                i.putExtra(MainActivity.MAIN_CARD, card);
                startActivity(i);
            }
        });

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i= new Intent(CardConfig.this, MainActivity.class);
                if(!TextUtils.isEmpty(curText.getText())){
                    CharSequence theText=curText.getText();
                    card.setName(theText.toString());
                }
                i.putExtra(MainActivity.MAIN_CARD, card);
                startActivity(i);
            }
        });

    }
}

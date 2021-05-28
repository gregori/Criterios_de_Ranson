package tech.gregori.criteriosderanson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MORTALITY = "tech.gregori.criteriosderanson.mortality";
    public static final String EXTRA_AGE = "tech.gregori.criteriosderanson.age";
    public static final String EXTRA_SCORE = "tech.gregori.criteriosderanson.score";
    public static final String EXTRA_SERIOUS = "tech.gregori.criteriosderanson.serious";

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText leukocytesEditText;
    private EditText bloodSugarEditText;
    private EditText astEditText;
    private EditText ldhEditText;
    private CheckBox litiasysCheckBox;
    private int score;
    private boolean serious;
    private int mortality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ageEditText = findViewById(R.id.age_editText);
        nameEditText = findViewById(R.id.name_editText);
        leukocytesEditText = findViewById(R.id.leucocites_editText);
        bloodSugarEditText = findViewById(R.id.glico_editText);
        astEditText = findViewById(R.id.ast_editText);
        ldhEditText = findViewById(R.id.ldh_editText);
        litiasysCheckBox = findViewById(R.id.litiase_checkBox);
    }

    // launchSecondActivity
    public void calculateRansonCriteria(View view) {
        int age = Integer.parseInt(ageEditText.getText().toString());
        int leuko = Integer.parseInt(leukocytesEditText.getText().toString());
        double glico = Double.parseDouble(bloodSugarEditText.getText().toString());
        int ast = Integer.parseInt(astEditText.getText().toString());
        int ldh = Integer.parseInt(ldhEditText.getText().toString());
        // pegar os valores

        if (litiasysCheckBox.isChecked()) {
            // comparar valores com checkbox

            //if Idade > 70 anos  pontuacao++
            if (age > 70) score++;
//            if Leucócitos > 18000 células/mm3 pontuacao++
            if (leuko > 18000) score++;
//            Glicemia > 12.2 mmol/L
            if (glico > 12.2) score++;
//            AST > 250 UI/L
            if (ast > 250) score++;
//            LDH > 400 UI/L
            if (ldh > 400) score++;

        } else {
//            if Idade maior a 55 anos pontuacao++
            if (age > 55) score++;

//            Leucócitos maior a 16000 células/mm3
            if (leuko > 16000) score++;

//            Glicemia maior a 11 mmol/L
            if (glico > 11) score++;

//            AST/TGO sérico maior a 250 UI/L
            if (ast > 250) score++;

//            LDH sérico maior a 350 UI/L
            if (ldh > 350) score++;

        }
        // pontuacao = valor?
        // if pontuacao >= 3  grave = true;
        // else grave = false
        serious = (score >= 3);

        /*
        Pontuação 0 a 2 : 2% de mortalidade
        if pontuacao <= 2 mortalidade = 2
        Pontuação de 3 a 4 : 15% de mortalidade
        else if pontuacao <= 4 mortalidade = 15
        Pontuação de 5 a 6 : 40% de mortalidade
        else if pontuacao <= 6 mortalidade = 40
        Pontuação de 7 a 8 : 100% de mortalidade
        else mortalidade = 100
         */
        if (score <= 2) mortality = 2;
        else if (score <= 4) mortality = 15;
        else if (score <= 6) mortality = 40;
        else mortality = 100;

        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra(EXTRA_MORTALITY, mortality);
        intent.putExtra(EXTRA_AGE, age);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_SERIOUS, serious);
        startActivity(intent);
    }
}
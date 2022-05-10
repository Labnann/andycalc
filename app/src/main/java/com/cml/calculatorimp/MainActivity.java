package com.cml.calculatorimp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    TextView result;
    Expression expression;
    Button equalSign, copier;
    EditText input;
    ClipData clip  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result =  findViewById(R.id.viewer);

        input =  findViewById(R.id.editor);
        equalSign =  findViewById(R.id.equalSign);
        copier =  findViewById(R.id.copier);

        equalSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression = new Expression();
                expression.setExpression(input.getText().toString());
                //  System.out.println(input.getText().toString());
                expression.calculate();
                result.setText("\n"+expression.getResult());
                equalSign.setText(new EmojiSelector().getEmoji());
            }
        });

        copier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clip = ClipData.newPlainText("ResultCopy",expression.getResult());
                clipboard.setPrimaryClip(clip);
            }
        });

    }
}

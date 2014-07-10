package me.relex.underline;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MyActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView text1 = (TextView) findViewById(R.id.text_1);
        TextView text2 = (TextView) findViewById(R.id.text_2);
        UnderlineTextView text3 = (UnderlineTextView) findViewById(R.id.text_3);

        String content = getString(R.string.hello);

        // 系统原生下划线
        SpannableStringBuilder spanString = new SpannableStringBuilder(content);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text1.setText(spanString);

        // UnderlineImageSpan 下划线
        SpannableStringBuilder spanString2 = new SpannableStringBuilder(content);
        spanString2.setSpan(new UnderlineImageSpan(text2.getLineHeight(), text2.getPaint(),
                        content.substring(20, 25), text2.getTextColors().getDefaultColor()), 20, 25,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        text2.setText(spanString2);

        // UnderlineTextView 下划线
        text3.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

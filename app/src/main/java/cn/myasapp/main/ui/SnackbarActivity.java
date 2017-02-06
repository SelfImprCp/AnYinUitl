package cn.myasapp.main.ui;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2017/2/6.
 */

public class SnackbarActivity extends BaseActivity {

    @BindView(id = R.id.snackbar_test_button_text, click = true)
    private Button snackbar_test_button_text;

    @BindView(id = R.id.container)
    private CoordinatorLayout container;



    @BindView(id = R.id.a_textinputlayout )
    private TextInputLayout a_textinputlayout;



    private EditText a_editText1;






    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_snackbar_layout);
    }

    @Override
    protected void initView() {
        super.initView();


        a_textinputlayout.setHint("请输入用户名");

        a_editText1 = a_textinputlayout.getEditText();
        a_editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这儿判断操作，如果输入错误可以给用户提示
                if(s.length()<5){
                    a_textinputlayout.setErrorEnabled(true);
                    a_textinputlayout.setError("用户名不能小于6位");
                }else{
                    a_textinputlayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.snackbar_test_button_text:
               //
                // Snackbar.make(container, "SnackbarTest", Snackbar.LENGTH_LONG).show();
                Snackbar snackbar =   Snackbar.make(container, "SnackbarTest今天醚来",Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(container,"ActionClick",Snackbar.LENGTH_LONG).show();
                    }
                }) ;
                //
                setSnackbarMessageTextColor(snackbar, Color.parseColor("#FFFFFF"));
                snackbar.show();


                break;

        }
    }


    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(color);
    }
}

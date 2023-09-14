package test.connect.myapplication;

import static test.connect.myapplication.api.ApiClientFactory.GetCommentApi;
import static test.connect.myapplication.api.ApiClientFactory.GetPostApi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.connect.myapplication.api.SlimCallback;
import test.connect.myapplication.model.Comment;
import test.connect.myapplication.model.Post;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView apiText1 = findViewById(R.id.activity_main_textView1);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        Button postByPathBtn = findViewById(R.id.main_button_postByPath);
        Button postByBodyBtn = findViewById(R.id.main_button_postByBody);
        EditText firstNameIn = findViewById(R.id.main_editText_first);
        EditText lastNameIn = findViewById(R.id.main_editText_last);
        EditText commentIn = findViewById(R.id.main_editText_comment);


        GetCommentApi().GetAllComments().enqueue(new SlimCallback<List<Comment>>(comments ->{
            apiText1.setText("");

            for (int i = comments.size()-1; i >= 0; i--) {
                apiText1.append(comments.get(i).printable());
            }


        }, "GetAllComments"));
    }
}


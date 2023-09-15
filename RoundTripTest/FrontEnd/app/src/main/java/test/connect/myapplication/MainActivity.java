package test.connect.myapplication;

import static test.connect.myapplication.api.ApiClientFactory.GetCommentApi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import test.connect.myapplication.api.SlimCallback;
import test.connect.myapplication.model.Comment;

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

        RegenerateAllComments(apiText1);

        postByPathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCommentApi().PostCommentByPath(firstNameIn.getText().toString(), lastNameIn.getText().toString(), commentIn.getText().toString()).enqueue(new SlimCallback<Comment>(comment->{
                    RegenerateAllComments(apiText1);
                    firstNameIn.setText("");
                    lastNameIn.setText("");
                    commentIn.setText("");
                }));
            }
        });

        postByBodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment newComment = new Comment();
                newComment.setFirstName(firstNameIn.getText().toString());
                newComment.setLastName(lastNameIn.getText().toString());
                newComment.setComment(commentIn.getText().toString());
                GetCommentApi().PostCommentByBody(newComment).enqueue(new SlimCallback<Comment>(comment->{
                    RegenerateAllComments(apiText1);
                    firstNameIn.setText("");
                    lastNameIn.setText("");
                    commentIn.setText("");
                }));
            }
        });
    }

    void RegenerateAllComments(TextView apiText1) {
        GetCommentApi().GetAllComment().enqueue(new SlimCallback<List<Comment>>(comments ->{
            apiText1.setText("");

            for (int i = comments.size()-1; i >= 0; i--) {
                apiText1.append(comments.get(i).printable());
            }


        }, "GetAllComment"));
    }

}


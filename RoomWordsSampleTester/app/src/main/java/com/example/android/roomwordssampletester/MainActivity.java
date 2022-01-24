package com.example.android.roomwordssampletester;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;
    public static final String REQUEST_CODE = "request_code";
    public static final String WORD_ID = "word_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                intent.putExtra(REQUEST_CODE, NEW_WORD_ACTIVITY_REQUEST_CODE);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable  List<Word> words) {
                //Update the cached copy of the words in the adapter
                adapter.setWords(words);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Word myWord = adapter.getWordAtPosition(position);
                makeText(MainActivity.this, "Deleting " + myWord.getWord(), Toast.LENGTH_SHORT).show();

                mWordViewModel.deleteWord(myWord);
            }
        });

        helper.attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new WordListAdapter.ClickListener()  {
            @Override
            public void onItemClick(View v, int position) {
                int id = adapter.getWordAtPosition(position).getId();
                launchUpdateWordActivity(id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_data) {
            Toast.makeText(this, "Clearing All Data...", Toast.LENGTH_SHORT).show();
            mWordViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE) && (resultCode == RESULT_OK)){
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY_WORD));
            mWordViewModel.insert(word);
        }else if((requestCode == UPDATE_WORD_ACTIVITY_REQUEST_CODE) && (resultCode == RESULT_OK)) {
            String word = data.getStringExtra(NewWordActivity.EXTRA_REPLY_WORD);
            int id = data.getIntExtra(NewWordActivity.EXTRA_REPLY_ID, 0);
            Word updatedWord = new Word(id, word);
            mWordViewModel.updateWord(updatedWord);
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_SHORT)
                    .show();

        }
    }



    public void launchUpdateWordActivity(Integer id) {
        Intent intent = new Intent(this, NewWordActivity.class);
        intent.putExtra(MainActivity.REQUEST_CODE, MainActivity.UPDATE_WORD_ACTIVITY_REQUEST_CODE);
        intent.putExtra(MainActivity.WORD_ID, id);
        startActivityForResult(intent, MainActivity.UPDATE_WORD_ACTIVITY_REQUEST_CODE);

    }
}

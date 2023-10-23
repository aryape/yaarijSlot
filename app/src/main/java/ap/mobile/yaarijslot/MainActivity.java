package ap.mobile.yaarijslot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private ImageView slot1, slot2, slot3;
    private Thread thread1, thread2, thread3;
    private Handler handler;
    private Button button;
    private Random random = new Random();
    private int[] images = {
            R.drawable.slot_1, R.drawable.slot_2, R.drawable.slot_3,
            R.drawable.slot_4, R.drawable.slot_5, R.drawable.slot_6,
            R.drawable.slot_7, R.drawable.slot_8, R.drawable.slot_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btStartStop).setOnClickListener(this);

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText("Arya Pradipta | 215150401111002\nM Fayzul Haq | 215150400111002");

        this.slot1 = (ImageView) findViewById(R.id.ivSlot1);
        this.slot2 = (ImageView) findViewById(R.id.ivSlot2);
        this.slot3 = (ImageView) findViewById(R.id.ivSlot3);
        this.button = (Button) findViewById(R.id.btStartStop);
        this.handler = new Handler(Looper.getMainLooper());
        createThread();
    }

    private void createThread() {
        this.thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        final int randomIndex = random.nextInt(images.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                slot1.setImageResource(images[randomIndex]);
                            }
                        });
                        Thread.sleep(50);
                    }
                } catch (Exception e) {

                }
            }
        });

        this.thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        final int randomIndex = random.nextInt(images.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                slot2.setImageResource(images[randomIndex]);
                            }
                        });
                        Thread.sleep(50);
                    }
                } catch (Exception e) {

                }
            }
        });

        this.thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        final int randomIndex = random.nextInt(images.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                slot3.setImageResource(images[randomIndex]);
                            }
                        });
                        Thread.sleep(50);
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (this.thread1.isAlive()) {
            this.thread1.interrupt();
        } else if (this.thread2.isAlive()) {
            this.thread2.interrupt();
        } else if (this.thread3.isAlive()) {
            this.thread3.interrupt();
            button.setText("Start");
        } else {
            this.createThread();
            this.thread1.start();
            this.thread2.start();
            this.thread3.start();
            button.setText("Stop");
        }
    }
}
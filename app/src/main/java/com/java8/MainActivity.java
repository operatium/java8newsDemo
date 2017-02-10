package com.java8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(v -> lambda1());
        Button button2 = (Button)findViewById(R.id.button3);
        button2.setOnClickListener(v -> lambda2(1));
        Button button3 = (Button)findViewById(R.id.button4);
        button3.setOnClickListener(v -> lambda2(2));
        Button button4 = (Button)findViewById(R.id.button5);
        button4.setOnClickListener(v -> optional());
    }

    public void lambda1()
    {
        //        new Runnable() {
//            @Override
//            public void run() {
//                Log.e("show","run");
//            }
//        }.run();

        Runnable r = () -> Log.e("show", "run");
        r.run();
    }

    @FunctionalInterface//注释的作用 要求接口只有一个函数 符合lambda语法 超过1个函数就报错
    interface Converter<F, T> {
        T convert(F from);
    }

    public void lambda2(int str)
    {
        Converter<String, Integer> converter = null;
        if (str == 1)
            //方式1:
            converter = (from) -> Integer.valueOf(from);
        if (str == 2)
            //方式2:
            converter = Integer::valueOf;//Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用

        Integer converted = converter.convert("123");
        Toast.makeText(this,converted+"",Toast.LENGTH_SHORT).show();

//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());//SDK24以上
    }

    public void optional()
    {
        Integer a = null;
        Integer b = new Integer(10);
        Optional<Integer> valueA = Optional.ofNullable(a);
        Optional<Integer> valueB = Optional.of(b);
        Integer res = optionAdd(valueA,valueB);
        Toast.makeText(this,res.toString(),Toast.LENGTH_SHORT).show();
    }

    public Integer optionAdd(Optional<Integer> a, Optional<Integer> b)
    {
        Integer va1 = a.orElse(new Integer(0));
        Integer va2 = b.get();
        return va1+va2;
    }
}

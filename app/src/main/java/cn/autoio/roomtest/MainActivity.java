package cn.autoio.roomtest;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.autoio.roomtest.databinding.ActivityMainBinding;
import cn.autoio.roomtest.entity.User;
import cn.autoio.roomtest.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    UserViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        binding.installView.setOnClickListener(v -> {
            User user = new User();
            user.setFirstName("Gavin");
            user.setLastName("Novate");
            viewModel.installUser(user);
        });

        viewModel.observeAllUser().observe(this, users -> {
            if (users != null)
                Log.d(TAG, "onChanged: " + users);
        });
    }
}

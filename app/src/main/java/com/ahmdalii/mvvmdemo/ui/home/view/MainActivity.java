package com.ahmdalii.mvvmdemo.ui.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ahmdalii.mvvmdemo.R;
import com.ahmdalii.mvvmdemo.model.ProductsResponse;
import com.ahmdalii.mvvmdemo.network.ProductsClient;
import com.ahmdalii.mvvmdemo.ui.home.repo.HomeRepoImpl;
import com.ahmdalii.mvvmdemo.ui.home.viewmodel.HomeViewModel;
import com.ahmdalii.mvvmdemo.ui.home.viewmodel.HomeViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private HomeViewModelFactory homeViewModelFactory;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gettingViewModelReady();
        getProducts();
    }

    private void gettingViewModelReady() {
        homeViewModelFactory = new HomeViewModelFactory(
                HomeRepoImpl.getInstance(
                        ProductsClient.getInstance()
                )
        );

        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);
    }

    private void getProducts() {
        homeViewModel.getProducts();

        homeViewModel.productsResponseMutableLiveData.observe(this, new Observer<ProductsResponse>() {
            @Override
            public void onChanged(ProductsResponse productsResponse) {
                Log.d("weAreFinished:", productsResponse.toString());
            }
        });
    }
}
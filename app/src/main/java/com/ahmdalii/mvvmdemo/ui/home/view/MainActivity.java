package com.ahmdalii.mvvmdemo.ui.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ahmdalii.mvvmdemo.R;
import com.ahmdalii.mvvmdemo.model.ProductsResponse;
import com.ahmdalii.mvvmdemo.network.ProductsClient;
import com.ahmdalii.mvvmdemo.ui.home.repo.HomeRepoImpl;
import com.ahmdalii.mvvmdemo.ui.home.viewmodel.HomeViewModel;
import com.ahmdalii.mvvmdemo.ui.home.viewmodel.HomeViewModelFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HomeViewModelFactory homeViewModelFactory;
    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gettingViewModelReady();
        initProductRecyclerView();
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

    private void initProductRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
    }

    private void getProducts() {
        homeViewModel.getProducts();

        homeViewModel.productsResponseMutableLiveData.observe(this, new Observer<ProductsResponse>() {
            @Override
            public void onChanged(ProductsResponse productsResponse) {
                Log.d("weAreFinished:", productsResponse.toString());
                productAdapter.setDataToAdapter(productsResponse.getProducts());
            }
        });
    }
}
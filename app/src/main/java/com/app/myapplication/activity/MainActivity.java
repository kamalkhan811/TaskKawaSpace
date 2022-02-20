package com.app.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.app.myapplication.R;
import com.app.myapplication.adapter.CardVerticalListAdapter;
import com.app.myapplication.adapter.TopCrouselAdapter;
import com.app.myapplication.model.PeopleCardsResponse;
import com.app.myapplication.model.ResultsItem;
import com.app.myapplication.viewmodel.UserViewModel;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    int selectedPosition=0;
    private TopCrouselAdapter topCrouselAdapter;
    private CardVerticalListAdapter cardVerticalListAdapter;
    private RecyclerView rvCrousel,rvCards;
    private ProgressBar progressBar;
    UserViewModel userViewModel;
    ImageView ivRightArrow,ivLeftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCards=findViewById(R.id.rv_cards);
        rvCrousel=findViewById(R.id.rv_crousel);
        progressBar=findViewById(R.id.progressbar);
        ivLeftArrow=findViewById(R.id.iv_left_arrow);
        ivRightArrow=findViewById(R.id.iv_right_arrow);
        init();
    }
    private void init(){
        userViewModel= new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init();
        topCrouselAdapter=new TopCrouselAdapter();
        cardVerticalListAdapter=new CardVerticalListAdapter();
        setListeners();
        setRecyclerView();
        getUsersData();
    }

    private void setListeners() {
        ivLeftArrow.setOnClickListener(view -> {
            if(selectedPosition>0){
                selectedPosition=selectedPosition-1;
                ((LinearLayoutManager) Objects.requireNonNull(rvCrousel.getLayoutManager())).scrollToPositionWithOffset(selectedPosition, 0);
                ((LinearLayoutManager) Objects.requireNonNull(rvCards.getLayoutManager())).scrollToPositionWithOffset(selectedPosition, 0);
                cardVerticalListAdapter.selectItemFromTopCrousel(selectedPosition);
            }

        });
        ivRightArrow.setOnClickListener(view -> {
            if(selectedPosition<topCrouselAdapter.getItemCount()-1){
                selectedPosition = selectedPosition + 1;
                ((LinearLayoutManager) Objects.requireNonNull(rvCrousel.getLayoutManager())).scrollToPositionWithOffset(selectedPosition, 0);
                ((LinearLayoutManager) Objects.requireNonNull(rvCards.getLayoutManager())).scrollToPositionWithOffset(selectedPosition, 0);
                cardVerticalListAdapter.selectItemFromTopCrousel(selectedPosition);
            }
        });

    }


    private void getUsersData() {
        userViewModel.getUsersLiveData("gender,name,nat,location,picture,email",20).observe(this, result -> {
            switch (result.status){
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    assert result.data != null;
                    setScreenData(result.data);
                    //show data
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show();
                    //show error
                    break;
            }
        });
    }

    private void setScreenData(PeopleCardsResponse data) {
        if(data.getResults().size()>0){
            topCrouselAdapter.setListItems(data.getResults());
            cardVerticalListAdapter.setListItems(data.getResults());
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRecyclerView(){
        rvCrousel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        rvCrousel.setAdapter(topCrouselAdapter);
        topCrouselAdapter.setClickItemListener(this::crousalOnItemClick);
        rvCards.setAdapter(cardVerticalListAdapter);
        cardVerticalListAdapter.setClickItemListener(this::cardOnItemClick);

    }
    void cardOnItemClick(int position,ResultsItem data){
        selectedPosition=position;
        ((LinearLayoutManager) Objects.requireNonNull(rvCrousel.getLayoutManager())).scrollToPositionWithOffset(position, 0);
    }
    void crousalOnItemClick(int position,ResultsItem data){}

}
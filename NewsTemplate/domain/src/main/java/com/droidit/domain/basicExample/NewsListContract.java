package com.droidit.domain.basicExample;


import com.droidit.domain.BaseContract;
import com.droidit.domain.posts.PostDto;

import java.util.List;

/**
 * Created by JohannesC on 05-Sep-16.
 * This makes it easier to understand how your application modules work
 */
public interface NewsListContract {

    interface Presenter extends BaseContract.Presenter<View> {
        void onConnectionButtonClicked();

        void onGetPostsBtnClick();
    }

    interface View extends BaseContract.View {

        void displaySinglePostTitle(String title);

        void displayConnectionError(String message);

        void setupInitialList();

        void updateListWithNewData(final List<PostDto> postDtos);
    }

    interface WireFrame {
        void onConnectionButtonClicked();
    }
}

package com.droidit.domain.basicExample;

import com.droidit.domain.DefaultCallback;
import com.droidit.domain.posts.PostDto;
import com.droidit.domain.posts.PostInteractor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by JohannesC on 05-Sep-16.
 * View tells the Presenter about state and the Presenter instructs the View to do something.
 */
public class NewsListPresenter implements NewsListContract.Presenter {

    private final NewsListContract.WireFrame wireframe;
    private final PostInteractor postInteractor;
    private NewsListContract.View view;

    @Inject
    public NewsListPresenter(NewsListContract.WireFrame basicExampleWireframe, PostInteractor postInteractor) {
        wireframe = basicExampleWireframe;
        this.postInteractor = postInteractor;
    }

    @Override
    public void onViewAttached(NewsListContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.setupInitialList();
    }

    @Override
    public void onGetPostsBtnClick() {
        postInteractor.getPosts(new DefaultCallback<List<PostDto>>() {
            @Override
            public void onSuccess(List<PostDto> success) {
                view.updateListWithNewData(success);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.displayConnectionError(throwable.getMessage());
            }
        });
    }
}

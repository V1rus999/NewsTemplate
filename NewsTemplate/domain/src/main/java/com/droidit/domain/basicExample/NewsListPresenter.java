package com.droidit.domain.basicExample;

import com.droidit.domain.DefaultCallback;
import com.droidit.domain.posts.PostDto;
import com.droidit.domain.posts.PostInteractor;

import java.util.ArrayList;
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
        view.setupMenuAndToolbar();
        view.setupInitialList();
        view.setupSwipeToRefresh();
//        view.displayBusyIndicator();
        view.updateListWithNewData(provideFakeList());
        view.hideBusyIndicator();
//        postInteractor.getPosts(postListCallback);
    }

    @Override
    public void onSwipeToRefresh() {
        postInteractor.getPosts(postListCallback);
    }

    private final DefaultCallback<List<PostDto>> postListCallback = new DefaultCallback<List<PostDto>>() {
        @Override
        public void onSuccess(final List<PostDto> success) {
            view.updateListWithNewData(success);
            view.hideBusyIndicator();
        }

        @Override
        public void onFailure(Throwable throwable) {
            view.displayConnectionError(throwable.getMessage());
            view.hideBusyIndicator();
        }
    };

    private List<PostDto> provideFakeList() {
        final List<PostDto> success = new ArrayList<>();
        PostDto postDto = new PostDto();
        postDto.title = "Blind man walks 3km to fetch water";
        postDto.body = "Pietermaritzburg - A blind man from Elandskop in Pietermaritzburg has to walk 3km to fetch water for himself and his mentally disabled sister, GroundUp reports.\n" +
                "\n" +
                "Ernest Mdunge, 49, and his sister Bongekile Hlengwa, 46, live in a dilapidated hut built with bricks left over from a demolished house. The roof is made of grass, and rain and wind enter between the stacked bricks.\n" +
                "\n" +
                "The family has no tap in the yard and Mdunge has to walk 3km to the \"Jojo\" water tank in the area.\n" +
                "\n" +
                "READ: Tanks installed at schools - thanks to learners\n" +
                "\n" +
                "\"I use my feet to feel and guess if I’m still on the right path. Sometimes I hit myself on things and when that happens, I miss the path. I have to take steps back and try to find a spot that is familiar to my feet for me to find my way back.";

        for (int i = 0; i < 12; i++) {
            success.add(postDto);
        }
        return success;
    }
}

package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularTvShowsUseCase;
import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.GetImdbMostPopularTvShows;
import pl.hypeapp.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.InsertTvShowToMostPopular;
import pl.hypeapp.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShows;
import pl.hypeapp.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShowsUseCase;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.GetImdbTopTvShows;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.InsertTvShowToTopList;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopList;
import pl.hypeapp.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopListUseCase;
import pl.hypeapp.core.usecase.tvshow.update.GetTvShowsUpdates;
import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsUseCase;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetTvShowUseCase getTvShowUseCase(GetTvShowFromDatabase getTvShowFromDatabase) {
        return new GetTvShowUseCase(getTvShowFromDatabase);
    }

    @Bean
    public CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase(GetImdbTopTvShows getImdbTopTvShows, GetTvShowFromApi getTvShowFromApi,
                                                                     GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToTopList insertTvShowToTopList,
                                                                     InsertTvShowToDatabase insertTvShowToDatabase) {
        return new CollectImdbTopTvShowsUseCase(getImdbTopTvShows, getTvShowFromApi, getTvShowIdFromApi, insertTvShowToTopList, insertTvShowToDatabase);
    }

    @Bean
    public CollectImdbMostPopularTvShowsUseCase collectImdbMostPopularTvShowsUseCase(GetImdbMostPopularTvShows getImdbMostPopularTvShows, GetTvShowFromApi getTvShowFromApi,
                                                                                     GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToMostPopular insertTvShowToMostPopular,
                                                                                     InsertTvShowToDatabase insertTvShowToDatabase) {
        return new CollectImdbMostPopularTvShowsUseCase(getImdbMostPopularTvShows, getTvShowFromApi, getTvShowIdFromApi, insertTvShowToMostPopular, insertTvShowToDatabase);
    }

    @Bean
    public UpdateTvShowsUseCase updateTvShowsUseCase(GetTvShowFromApi getTvShowFromApi, GetTvShowFromDatabase getTvShowFromDatabase,
                                                     GetTvShowsUpdates getTvShowsUpdates, InsertTvShowToDatabase insertTvShowToDatabase) {
        return new UpdateTvShowsUseCase(getTvShowFromApi, getTvShowFromDatabase, getTvShowsUpdates, insertTvShowToDatabase);
    }

    @Bean
    public GetTvShowTopListUseCase getTvShowTopListUseCase(GetTvShowTopList getTvShowTopList) {
        return new GetTvShowTopListUseCase(getTvShowTopList);
    }

    @Bean
    public GetMostPopularTvShowsUseCase getMostPopularTvShowsUseCase(GetMostPopularTvShows getMostPopularTvShows) {
        return new GetMostPopularTvShowsUseCase(getMostPopularTvShows);
    }

}

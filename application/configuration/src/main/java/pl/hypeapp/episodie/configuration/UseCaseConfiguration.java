package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowFromDatabase;
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopTvShowsUseCase;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.GetImdbTopTvShows;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.GetTvShowIdFromApi;
import pl.hypeapp.core.usecase.tvshow.toplist.collectimdbtoptvshows.InsertTvShowToTopList;
import pl.hypeapp.core.usecase.tvshow.update.GetTvShowsUpdates;
import pl.hypeapp.core.usecase.tvshow.update.UpdateTvShowsUseCase;

@Configuration
public class UseCaseConfiguration {

    //    @Bean
//    public GetTvShowUseCase getTvShowUseCase(DoesTvShowExist doesTvShowExist, GetTvShowFromApi getTvShowFromApi,
//                                             GetTvShowFromDatabase getTvShowFromDatabase, InsertTvShowToDatabase insertTvShowToDatabase){
//        return new GetTvShowUseCase(doesTvShowExist, getTvShowFromApi, getTvShowFromDatabase, insertTvShowToDatabase);
//    }
//
    @Bean
    public GetTvShowUseCase getTvShowUseCase(GetTvShowFromApi getTvShowFromApi) {
        return new GetTvShowUseCase(getTvShowFromApi);
    }

    @Bean
    public CollectImdbTopTvShowsUseCase collectImdbTopTvShowsUseCase(GetImdbTopTvShows getImdbTopTvShows, GetTvShowFromApi getTvShowFromApi,
                                                                     GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToTopList insertTvShowToTopList,
                                                                     InsertTvShowToDatabase insertTvShowToDatabase) {
        return new CollectImdbTopTvShowsUseCase(getImdbTopTvShows, getTvShowFromApi, getTvShowIdFromApi, insertTvShowToTopList, insertTvShowToDatabase);
    }

    @Bean
    public UpdateTvShowsUseCase updateTvShowsUseCase(GetTvShowFromApi getTvShowFromApi, GetTvShowFromDatabase getTvShowFromDatabase,
                                                     GetTvShowsUpdates getTvShowsUpdates, InsertTvShowToDatabase insertTvShowToDatabase) {
        return new UpdateTvShowsUseCase(getTvShowFromApi, getTvShowFromDatabase, getTvShowsUpdates, insertTvShowToDatabase);
    }

}

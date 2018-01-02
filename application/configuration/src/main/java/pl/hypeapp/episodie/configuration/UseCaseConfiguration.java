package pl.hypeapp.episodie.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hypeapp.episodie.core.usecase.job.RecordJobResult;
import pl.hypeapp.episodie.core.usecase.job.RecordJobResultUseCase;
import pl.hypeapp.episodie.core.usecase.job.SendSmsAboutFailedJob;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowFromDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.GetTvShowIdFromApi;
import pl.hypeapp.episodie.core.usecase.tvshow.InsertTvShowToDatabase;
import pl.hypeapp.episodie.core.usecase.tvshow.gettvshow.GetTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.CollectImdbMostPopularUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.GetImdbMostPopularTvShows;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.InsertTvShowToMostPopular;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularTvShows;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.getmostpopular.GetMostPopularUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.CollectPremieresUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.GetImdbTvShowsPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.InsertPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetEpisodePremieresLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetEpisodePremieresRemote;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetPremieresUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.getpremieres.GetTvShowPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShow;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShowInApi;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShowUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.CollectImdbTopListUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.GetImdbTopTvShows;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.collectimdbtoptvshows.InsertTvShowToTopList;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist.GetTopListUseCase;
import pl.hypeapp.episodie.core.usecase.tvshow.toplist.gettoplist.GetTvShowTopList;
import pl.hypeapp.episodie.core.usecase.tvshow.update.GetTvShowsUpdates;
import pl.hypeapp.episodie.core.usecase.tvshow.update.UpdateTvShowsUseCase;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetTvShowUseCase getTvShowUseCase(GetTvShowFromDatabase getTvShowFromDatabase, GetTvShowFromApi getTvShowFromApi,
                                             InsertTvShowToDatabase insertTvShowToDatabase) {
        return new GetTvShowUseCase(getTvShowFromDatabase, getTvShowFromApi, insertTvShowToDatabase);
    }

    @Bean
    public CollectImdbTopListUseCase collectImdbTopTvShowsUseCase(GetImdbTopTvShows getImdbTopTvShows, GetTvShowFromApi getTvShowFromApi,
                                                                  GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToTopList insertTvShowToTopList,
                                                                  InsertTvShowToDatabase insertTvShowToDatabase) {
        return new CollectImdbTopListUseCase(getImdbTopTvShows, getTvShowFromApi, getTvShowIdFromApi, insertTvShowToTopList, insertTvShowToDatabase);
    }

    @Bean
    public CollectImdbMostPopularUseCase collectImdbMostPopularTvShowsUseCase(GetImdbMostPopularTvShows getImdbMostPopularTvShows, GetTvShowFromApi getTvShowFromApi,
                                                                              GetTvShowIdFromApi getTvShowIdFromApi, InsertTvShowToMostPopular insertTvShowToMostPopular,
                                                                              InsertTvShowToDatabase insertTvShowToDatabase) {
        return new CollectImdbMostPopularUseCase(getImdbMostPopularTvShows, getTvShowFromApi, getTvShowIdFromApi, insertTvShowToMostPopular, insertTvShowToDatabase);
    }

    @Bean
    public CollectPremieresUseCase collectImdbPremieresUseCase(GetImdbTvShowsPremieres getImdbTvShowsPremieres, GetTvShowIdFromApi getTvShowIdFromApi,
                                                               GetTvShowFromApi getTvShowFromApi, InsertTvShowToDatabase insertTvShowToDatabase,
                                                               InsertPremieres insertPremieres, GetPremieresUseCase getPremieresUseCase) {
        return new CollectPremieresUseCase(getImdbTvShowsPremieres, getTvShowIdFromApi, getTvShowFromApi, getPremieresUseCase, insertTvShowToDatabase, insertPremieres);
    }

    @Bean
    public UpdateTvShowsUseCase updateTvShowsUseCase(GetTvShowFromApi getTvShowFromApi, GetTvShowFromDatabase getTvShowFromDatabase,
                                                     GetTvShowsUpdates getTvShowsUpdates, InsertTvShowToDatabase insertTvShowToDatabase) {
        return new UpdateTvShowsUseCase(getTvShowFromApi, getTvShowFromDatabase, getTvShowsUpdates, insertTvShowToDatabase);
    }

    @Bean
    public GetTopListUseCase getTopListUseCase(GetTvShowTopList getTvShowTopList) {
        return new GetTopListUseCase(getTvShowTopList);
    }

    @Bean
    public GetMostPopularUseCase getMostPopularUseCase(GetMostPopularTvShows getMostPopularTvShows) {
        return new GetMostPopularUseCase(getMostPopularTvShows);
    }

    @Bean
    public GetPremieresUseCase getPremieresUseCase(GetTvShowPremieres getTvShowPremieres, GetEpisodePremieresRemote getEpisodePremieresRemote,
                                                   GetEpisodePremieresLocal getEpisodePremieresLocal, GetTvShowUseCase getTvShowUseCase) {
        return new GetPremieresUseCase(getTvShowPremieres, getEpisodePremieresRemote, getEpisodePremieresLocal, getTvShowUseCase);
    }

    @Bean
    public SearchTvShowUseCase searchTvShowUseCase(SearchTvShow searchTvShow, SearchTvShowInApi searchTvShowInApi, GetTvShowFromApi getTvShowFromApi,
                                                   InsertTvShowToDatabase insertTvShowToDatabase) {
        return new SearchTvShowUseCase(searchTvShow, searchTvShowInApi, getTvShowFromApi, insertTvShowToDatabase);
    }

    @Bean
    public RecordJobResultUseCase recordJobResultUseCase(RecordJobResult recordJobResult, SendSmsAboutFailedJob sendSmsAboutFailedJob) {
        return new RecordJobResultUseCase(recordJobResult, sendSmsAboutFailedJob);
    }

}

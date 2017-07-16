package pl.hypeapp.episodie.dataproviders.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.GetImdbMostPopularTvShows;
import pl.hypeapp.episodie.core.usecase.tvshow.mostpopular.collectimdbmostpopulartvshows.ImdbMostPopularTvShowCrawlerFailException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ImdbMostPopularCrawlerDataProvider implements GetImdbMostPopularTvShows {

    private static final int PATH_SECOND_SEGMENT = 2;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbMostPopularCrawlerDataProvider.class);

    @Override
    public List<String> crawl(String imdbUrl) {
        Document document;
        try {
            document = getDocument(imdbUrl);
        } catch (IOException e) {
            LOGGER.info("Unable to get document: " + e.getMessage());
            throw new ImdbMostPopularTvShowCrawlerFailException();
        }
        return initCrawler(document);
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private List<String> initCrawler(Document document) {
        List<String> imdbTvShowIds = new LinkedList<>();
        try {
            for (int i = 1; i <= 100; i++) {
                Elements titleSection = document.select(".lister-list > tr:nth-child(" + i + ") > td:nth-child(2)");
                Element aHrefElement = titleSection.select("a").first();
                String tvShowUrl = aHrefElement.attr("href");
                imdbTvShowIds.add(tvShowUrl.split("/")[PATH_SECOND_SEGMENT]);
            }
        } catch (Exception e) {
            throw new ImdbMostPopularTvShowCrawlerFailException();
        }
        return imdbTvShowIds;
    }

}

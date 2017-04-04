package pl.hypeapp.episodie.dataproviders.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.core.usecase.episode.extractimdbtoptvshows.GetImdbTopTvShows;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ImdbTopTvShowsCrawlerDataProvider implements GetImdbTopTvShows {
    private static final int PATH_SECOND_SEGMENT = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbTopTvShowsCrawlerDataProvider.class);

    @Override
    public List<String> getImdbIds(String url) {
        System.out.println("CRAWLING");
        try {
            Document document = getDocument(url);
            return initCrawler(document);
        } catch (IOException ex) {
            LOGGER.info("Unable to get document");
            return null;
        }
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private List<String> initCrawler(Document document) {
        List<String> imdbTvShowIds = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            Elements titleColumn = document.select(".lister-list > tr:nth-child(" + i + ") > td:nth-child(2) > a:nth-child(1)");
            String showUrl = titleColumn.attr("href");
            imdbTvShowIds.add(showUrl.split("/")[PATH_SECOND_SEGMENT]);
        }
        return imdbTvShowIds;
    }
}

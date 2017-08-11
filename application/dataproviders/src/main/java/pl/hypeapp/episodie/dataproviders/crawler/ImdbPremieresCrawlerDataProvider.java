package pl.hypeapp.episodie.dataproviders.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres.GetImdbTvShowsPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectimdbtvshowpremieres.ImdbPremieresTvShowCrawlerFailException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ImdbPremieresCrawlerDataProvider implements GetImdbTvShowsPremieres {

    private static final String URL_TV_SHOWS_MAIN = "http://www.imdb.com/tv/";

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbPremieresCrawlerDataProvider.class);

    private static final int DATE = 1;

    @Override
    public List<ImdbPremiere> crawl() {
        Document document;
        try {
            document = getDocument(URL_TV_SHOWS_MAIN);
            String premieresUrl = getPremieresUrl(document);
            document = getDocument(premieresUrl);
            return initCrawler(document);
        } catch (IOException e) {
            LOGGER.info("Unable to get document: " + e.getMessage());
            throw new ImdbPremieresTvShowCrawlerFailException();
        } catch (ImdbPremieresTvShowCrawlerFailException e) {
            throw new ImdbPremieresTvShowCrawlerFailException();
        }
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private String getPremieresUrl(Document document) {
        Elements premieresButton = document.select(".nav > li:nth-child(2) > a:nth-child(1)");
        return "http://www.imdb.com/" + premieresButton.attr("href");
    }

    private List<ImdbPremiere> initCrawler(Document document) {
        Elements lister = document.select(".lister-list");
        Elements listerItems = lister.select("div.lister-item");
        return selectImdbIds(listerItems);
    }

    private List<ImdbPremiere> selectImdbIds(Elements listerItems) {
        return listerItems.stream()
                .map(element -> {
                    String imdbId = element.select("div.lister-item-image").attr("data-tconst");
                    String premiereDate = element.select("div.list-description").text().split(", ")[DATE];
                    return createPremiere(imdbId, premiereDate);
                }).collect(Collectors.toList());
    }

    private ImdbPremiere createPremiere(String imdbId, String premiereDate) {
        return ImdbPremiere.builder()
                .imdbId(imdbId)
                .premiereDate(formatStringToDate(premiereDate))
                .build();
    }

    private LocalDate formatStringToDate(String date) {
        try {
            return LocalDate.parse(date + " " + Year.now().toString(),
                    DateTimeFormatter.ofPattern("MMM. d yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new ImdbPremieresTvShowCrawlerFailException();
        }
    }

}

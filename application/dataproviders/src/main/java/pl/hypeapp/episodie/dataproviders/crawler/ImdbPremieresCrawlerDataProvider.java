package pl.hypeapp.episodie.dataproviders.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hypeapp.episodie.core.entity.crawler.ImdbPremiere;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.GetImdbTvShowsPremieres;
import pl.hypeapp.episodie.core.usecase.tvshow.premieres.collectpremieres.ImdbPremieresTvShowCrawlerFailException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ImdbPremieresCrawlerDataProvider implements GetImdbTvShowsPremieres {

    private static final String URL_TV_SHOWS_MAIN = "http://www.imdb.com/tv/";

    private static final Logger LOGGER = LoggerFactory.getLogger(ImdbPremieresCrawlerDataProvider.class);

    private static final int DATE = 1;

    @Override
    public List<ImdbPremiere> crawl() {
        try {
            return initCrawler();
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

    private String getPremieresUrl(Document document, int page) {
        Elements premieresButton = document.select(".nav > li:nth-child(2) > a:nth-child(1)");
        return "http://www.imdb.com/" + premieresButton.attr("href") + "&sort=list_order,asc&st_dt=&mode=detail&page=" + page;
    }

    private List<ImdbPremiere> initCrawler() throws IOException {
        Document document = getDocument(URL_TV_SHOWS_MAIN);
        List<ImdbPremiere> imdbPremieres = new ArrayList<>();
        try {
            for (int i = 1; i <= 3; i++) {
                String premieresUrl = getPremieresUrl(document, i);
                Document page = getDocument(premieresUrl);
                Elements lister = page.select(".lister-list");
                Elements listerItems = lister.select("div.lister-item");
                if (listerItems.size() > 1) {
                    imdbPremieres.addAll(selectImdbIds(listerItems));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImdbPremieresTvShowCrawlerFailException();
        }
        imdbPremieres.forEach(imdbPremiere -> {
            LOGGER.info(imdbPremiere.getImdbId() + " " + imdbPremiere.getPremiereDate());
        });
        return imdbPremieres;
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
        String date = premiereDate.replaceAll("\\.", "").substring(0, 3) + premiereDate.split(" ")[1];
        return ImdbPremiere.builder()
                .imdbId(imdbId)
                .premiereDate(formatStringToDate(date))
                .build();
    }

    private LocalDate formatStringToDate(String date) {
        try {
            return LocalDate.parse(date + " " + Year.now().toString(),
                    DateTimeFormatter.ofPattern("MMMd yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            LOGGER.info(e.getMessage());
            throw new ImdbPremieresTvShowCrawlerFailException();
        }
    }

}

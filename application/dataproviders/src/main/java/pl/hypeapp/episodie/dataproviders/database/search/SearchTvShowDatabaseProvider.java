package pl.hypeapp.episodie.dataproviders.database.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;
import pl.hypeapp.episodie.core.entity.database.TvShowLocal;
import pl.hypeapp.episodie.core.usecase.tvshow.search.SearchTvShow;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SearchTvShowDatabaseProvider implements SearchTvShow {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<TvShowLocal> search(String query) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(TvShowLocal.class)
                .get();

        Query searchQuery = queryBuilder
                .keyword()
                .onField("name")
                .matching(query)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery, TvShowLocal.class);

        return jpaQuery.getResultList();
    }

}

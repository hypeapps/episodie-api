package pl.hypeapp.episodie.entrypoints.rest.tvshow

import pl.hypeapp.core.entity.api.tvmaze.EpisodeRemote
import pl.hypeapp.core.usecase.tvshow.gettvshow.EpisodeNotFoundException
import pl.hypeapp.core.usecase.tvshow.gettvshow.GetTvShowUseCase
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException
import spock.lang.Specification

class GetTvShowRemoteEndpointTest extends Specification {
    String TV_MAZE_ID = "23"
    GetTvShowUseCase getEpisodeUseCase = Mock(GetTvShowUseCase)
    GetTvShowEndpoint getEpisodeEndpoint = new GetTvShowEndpoint(getEpisodeUseCase)

    def "shouldReturnsEpisode"() {
        given:
        EpisodeDto episodeDto
        getEpisodeUseCase.getEpisode(TV_MAZE_ID) >> new EpisodeRemote(TV_MAZE_ID)
        when:
        episodeDto = getEpisodeEndpoint.getTvShow(TV_MAZE_ID)
        then:
        notThrown(NotFoundException)
        episodeDto.getTvmazeId() != null
        episodeDto.getTvmazeId() == TV_MAZE_ID
    }

    def "shouldThrowErrorWhenEpisodeNotFound"() {
        given:
        getEpisodeUseCase.getEpisode(TV_MAZE_ID) >> { throw new EpisodeNotFoundException() }
        when:
        getEpisodeEndpoint.getTvShow(TV_MAZE_ID)
        then:
        thrown(NotFoundException)
    }
}

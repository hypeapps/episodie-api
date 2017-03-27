package pl.hypeapp.episodie.entrypoints.rest.episode

import pl.hypeapp.core.entity.Episode
import pl.hypeapp.core.usecase.getepisode.EpisodeNotFoundException
import pl.hypeapp.core.usecase.getepisode.GetEpisodeUseCase
import pl.hypeapp.episodie.entrypoints.rest.exception.NotFoundException
import spock.lang.Specification

class GetEpisodeEndpointTest extends Specification {
    String TV_MAZE_ID = "23"
    GetEpisodeUseCase getEpisodeUseCase = Mock(GetEpisodeUseCase)
    GetEpisodeEndpoint getEpisodeEndpoint = new GetEpisodeEndpoint(getEpisodeUseCase)

    def "shouldReturnsEpisode"() {
        given:
        EpisodeDto episodeDto
        getEpisodeUseCase.getEpisode(TV_MAZE_ID) >> new Episode(TV_MAZE_ID)
        when:
        episodeDto = getEpisodeEndpoint.getEpisode(TV_MAZE_ID)
        then:
        notThrown(NotFoundException)
        episodeDto.getTvmazeId() != null
        episodeDto.getTvmazeId() == TV_MAZE_ID
    }

    def "shouldErrorWhenEpisodeNotFound"() {
        given:
        getEpisodeUseCase.getEpisode(TV_MAZE_ID) >> { throw new EpisodeNotFoundException() }
        when:
        getEpisodeEndpoint.getEpisode(TV_MAZE_ID)
        then:
        thrown(NotFoundException)
    }
}
